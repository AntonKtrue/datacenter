package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;


import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;


import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.*;

import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;
import tn.kaz.ospas.view.funcrequirements.components.AgreementorWindow;
import tn.kaz.ospas.view.funcrequirements.components.OneToManyField;
import tn.kaz.ospas.view.funcrequirements.components.SequenceTextContainer;


import javax.persistence.Query;
import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton on 20.01.2017.
 */
public class FuncRequirementEditor extends VerticalLayout {
    private TransneftStructure structure;
    private SimpleJPAContainer<FuncRequirement> funcRequirementDs;
    private BeanFieldGroup<FuncRequirement> binder;
    private FuncRequirement funcRequirement;
   // private CrudButtons<FuncRequirement> crudButtons;
    private FormLayout layout;
    private Button saveButton;
    private Button printFt;

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public FuncRequirementEditor(TransneftStructure structure, SimpleJPAContainer<FuncRequirement> funcRequirementDs ) {
        this.structure = structure;
        this.funcRequirementDs = funcRequirementDs;
        this.funcRequirement = new FuncRequirement(structure);
        Query query = funcRequirementDs.getEntityProvider().getEntityManager().createNamedQuery("Number.empty");
        List<Long> result  = query.getResultList();
        this.funcRequirement.setNumber(result.size() > 0 ? result.get(0) : 1);
        buildFuncRequirementScreen();
    }

    public FuncRequirementEditor(SimpleJPAContainer<FuncRequirement> funcRequirementDs, Object itemId) {
        this.funcRequirement = funcRequirementDs.getItem(itemId).getEntity();
        this.funcRequirementDs = funcRequirementDs;
        buildFuncRequirementScreen();
        addCommitedContent();
    }

    private void addAgreementorsArea() {
        final JPAContainer<Agreementor> agreementorsDs = JPAContainerFactory.make(Agreementor.class, Config.JPA_UNIT);
        agreementorsDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
        agreementorsDs.applyFilters();
        final OneToManyField<Agreementor> agreementors = new OneToManyField<Agreementor>(
                "Согласующие",
                binder,agreementorsDs ,
                new Object[]{"id","sequence", "employee", "rank", "department"},
                new String[]{"#","Порядок","Сотрудник","Должность","Отдел"},
                "agreementors"
        );
        agreementors.addListenerToAddButton(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
                window.create();
            }
        });
        agreementors.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });

        addComponent(agreementors);
    }
    private void addCauseArea() {
        final SimpleJPAContainer<FRCause> frCauseDs = new SimpleJPAContainer<FRCause>(FRCause.class);
        frCauseDs.setApplyFiltersImmediately(false);
        frCauseDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
        frCauseDs.applyFilters();
        SequenceTextContainer<FRCause> frCauseArea = new SequenceTextContainer<FRCause>(FRCause.class, frCauseDs, "Основания доработки", 800f, funcRequirement );
        addComponent(frCauseArea);
    }

    private void addDescriptionArea() {
        final SimpleJPAContainer<Description> frDescriptionDs = new SimpleJPAContainer<Description>(Description.class);
        frDescriptionDs.setApplyFiltersImmediately(false);
        frDescriptionDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
        frDescriptionDs.applyFilters();
        SequenceTextContainer<Description> frDescriptionArea = new SequenceTextContainer<Description>(Description.class, frDescriptionDs, "Подробное описание доработки", 800f, funcRequirement);
        addComponent(frDescriptionArea);
    }

    public void addCommitedContent() {
        if(funcRequirement.getFrFilePath() != null) {
            Link frFileLink = new Link("Документ ФТ " , new FileResource(new File(funcRequirement.getFrFilePath())));
            frFileLink.setTargetName("_blank");
            layout.addComponent(frFileLink);
        } else {
            addComponent(new FileUploader("Документ ФТ", 100000000l, Config.DOC_DIR, funcRequirement, funcRequirementDs));
        }

        addAgreementorsArea();
        addCauseArea();
        addDescriptionArea();
        addLimitDatesArea();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();
                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace();
                }
                funcRequirementDs.addEntity(binder.getItemDataSource().getBean());
                funcRequirementDs.refresh();
            }
        });

    }

    private void addLimitDatesArea() {
        PopupDateField developmentDate = new PopupDateField("Дата разработки ПО:");
        developmentDate.setDateFormat("dd.MM.yyyy");
        developmentDate.setRequired(true);

        binder.bind(developmentDate,"developmentDate" );
        if(funcRequirement.getDevelopmentDate() == null)
            developmentDate.setValue(new Date());

        PopupDateField standTestDate = new PopupDateField("Дата стендовых испытаний:");
        standTestDate.setDateFormat("dd.MM.yyyy");
        standTestDate.setRequired(true);
        binder.bind(standTestDate,"standTestDate" );
        if(funcRequirement.getStandTestDate() == null)
            standTestDate.setValue(new Date());

        PopupDateField implementationDate = new PopupDateField("Дата внедрения:");
        implementationDate.setDateFormat("dd.MM.yyyy");
        implementationDate.setRequired(true);
        binder.bind(implementationDate,"implementationDate" );
        if(funcRequirement.getImplementationDate() == null)
            implementationDate.setValue(new Date());

        layout.addComponents(developmentDate, standTestDate, implementationDate);

    }


    private void buildFuncRequirementScreen() {
        layout = new FormLayout();
        Label objectName = new Label(funcRequirement.getStructure().getName());
        layout.addComponent(objectName);
        layout.setSpacing(true);

        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);


        //crudButtons = new CrudButtons<FuncRequirement>(funcRequirementDs, binder, this);
        //layout.addComponent(crudButtons);
        saveButton = new Button("Сохранить");
        printFt = new Button("Напечатать ФТ");
        layout.addComponents(saveButton,printFt);
        printFt.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
//                try {
//                    //new MakeWordDoc(funcRequirement);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });


        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("250");
        field.setRequired(true);

        layout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(40);
        shortDescription.setNullRepresentation("");
        binder.bind(shortDescription,"shortDescription");
        layout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        date.setDateFormat("dd.MM.yyyy");
        date.setRequired(true);
        binder.bind(date,"date" );
        date.setValue(new Date());

        layout.addComponent(date);
        addComponent(layout);


    }




}
