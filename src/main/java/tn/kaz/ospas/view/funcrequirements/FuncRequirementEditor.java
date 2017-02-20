package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;


import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;


import tn.kaz.ospas.data.HierarchicalJPAContainer;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.*;

import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.GuiHelper;
import tn.kaz.ospas.view.directories.staff.structure.StaffComponent;
import tn.kaz.ospas.view.funcrequirements.components.AgreementorWindow;
import tn.kaz.ospas.view.funcrequirements.components.StaffWindow;
import tn.kaz.ospas.view.funcrequirements.components.OneToManyField;
import tn.kaz.ospas.view.funcrequirements.components.SequenceTextContainer;


import java.util.Date;

/**
 * Created by Anton on 20.01.2017.
 */
public class FuncRequirementEditor extends TabSheet {
    private TransneftStructure structure;
    private SimpleJPAContainer<FuncRequirement> funcRequirementDs;
    private HierarchicalJPAContainer<TransneftStructure> structureDs;
    private BeanFieldGroup<FuncRequirement> binder;
    private FuncRequirement funcRequirement;

    private FormLayout layout;
    private Button saveButton;
    private Button printFt;

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public FuncRequirementEditor(SimpleJPAContainer<FuncRequirement> funcRequirementDs, HierarchicalJPAContainer<TransneftStructure> structureDs , Object itemId) {
        this.funcRequirement = funcRequirementDs.getItem(itemId).getEntity();
        this.funcRequirementDs = funcRequirementDs;
        this.structureDs = structureDs;
        addTab(GuiHelper.makeTabContent("Общие сведения", Alignment.MIDDLE_CENTER, descriptionTab()));
        addTab(GuiHelper.makeTabContent("Согласующие",Alignment.MIDDLE_CENTER, agreementorsTab()));
        addTab(GuiHelper.makeTabContent("Описание",Alignment.MIDDLE_CENTER,new FormLayout(causeBlock(), descriptionBlock())));
        addTab(GuiHelper.makeTabContent("Файлы",Alignment.MIDDLE_CENTER, filesTab()));
        addTab(GuiHelper.makeTabContent("Исполнители работ", Alignment.MIDDLE_CENTER, executorsTab()));
        addTab(GuiHelper.makeTabContent("Уведомления об изменениях", Alignment.MIDDLE_CENTER, noticeTab()));
        addTab(GuiHelper.makeTabContent("ПМИ", Alignment.MIDDLE_CENTER, pmiTab()));
        addTab(GuiHelper.makeTabContent("Печатные формы", Alignment.MIDDLE_CENTER, printeableTab()));
    }

    private Component pmiTab() {
        return new FormLayout();
    }

    private Component printeableTab()  {
        FormLayout formLayout = new FormLayout();
        //Long lim = 100000000l;
        //FileUploader fileUploader = new FileUploader("Функциональное требование", lim,   );
     //   Link frPrint = new Link("ФТ печать", new Reso);

        return new FormLayout();
    }

    private Component executorsTab() {
        return new FormLayout();
    }

    private Component noticeTab() {
        return new FormLayout();
    }

    private Component descriptionTab() {
       // VerticalLayout tabContent = new VerticalLayout();
        FormLayout formLayout = new FormLayout();

        Label objectName = new Label(funcRequirement.getStructure().getName());
        formLayout.addComponent(objectName);
        formLayout.setSpacing(true);
        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);
        saveButton = new Button("Сохранить");
        printFt = new Button("Напечатать ФТ");
        formLayout.addComponents(saveButton,printFt);
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
        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("250");
        field.setRequired(true);

        formLayout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(40);
        shortDescription.setNullRepresentation("");
        binder.bind(shortDescription,"shortDescription");
        formLayout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        date.setDateFormat("dd.MM.yyyy");
        date.setRequired(true);
        binder.bind(date,"date" );
        date.setValue(new Date());

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

        formLayout.addComponents(date, developmentDate, standTestDate, implementationDate);
        formLayout.setSpacing(true);
        formLayout.setSizeFull();
        return formLayout;
       // tabContent.addComponent(formLayout);
       // return tabContent;
    }

    private Component agreementorsTab() {
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
                //AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
                StaffWindow window = new StaffWindow();
               // window.create();
            }
        });
        agreementors.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });
        return new FormLayout(agreementors);

      //  layout.addComponent(agreementors);
    }

    private Component causeBlock() {
        final SimpleJPAContainer<FRCause> frCauseDs = new SimpleJPAContainer<FRCause>(FRCause.class);
        frCauseDs.setApplyFiltersImmediately(false);
        frCauseDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
        frCauseDs.applyFilters();
        SequenceTextContainer<FRCause> frCauseArea = new SequenceTextContainer<FRCause>(FRCause.class, frCauseDs, "Основания доработки", 800f, funcRequirement );
        return frCauseArea;
    }

    private Component descriptionBlock() {
        final SimpleJPAContainer<Description> frDescriptionDs = new SimpleJPAContainer<Description>(Description.class);
        frDescriptionDs.setApplyFiltersImmediately(false);
        frDescriptionDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
        frDescriptionDs.applyFilters();
        SequenceTextContainer<Description> frDescriptionArea = new SequenceTextContainer<Description>(Description.class, frDescriptionDs, "Подробное описание доработки", 800f, funcRequirement);
        return frDescriptionArea;
    }

    private Component filesTab() {
        FileUploader ftFile, pmiFile, asiFile, psiFile, actFile, protFile, exchangeActFile, noticeFile;
        Long lim = 100000000l;
        ftFile = new FileUploader("Документ ФТ", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "frFilePath");
        pmiFile = new FileUploader("Документ ПМИ", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "pmiFilePath" );
        asiFile = new FileUploader("Документ АСИ", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "asiFilePath");
        psiFile = new FileUploader("Документ ПСИ", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "psiFilePath");
        actFile = new FileUploader("Акт внедрения", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "actFilePath");
        protFile = new FileUploader("Протокол внедрения", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "protFilePath");
        exchangeActFile = new FileUploader("Акт передачи", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "exchangeActFilePath");
        noticeFile = new FileUploader("Уведомление", lim, Config.DOC_DIR, funcRequirement, funcRequirementDs, "noticeFilePath");
        return new FormLayout(ftFile, pmiFile, asiFile, psiFile, actFile, protFile, exchangeActFile, noticeFile);
    }

    private void addLimitDatesArea() {


        //layout.addComponents(developmentDate, standTestDate, implementationDate);

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
        addLimitDatesArea();
        layout.addComponent(date);
        layout.setSpacing(true);
        layout.setSizeFull();
        addComponent(layout);

    }

    private class StaffWindow extends Window {
        private StaffComponent staffComponent = new StaffComponent();
        public StaffWindow() {
            setContent(staffComponent);
            staffComponent.getEmployeeTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
                @Override
                public void itemClick(ItemClickEvent event) {
                    if(event.isDoubleClick() && ) {

                        StaffWindow.this.close();
                    } else {

                    }
                }
            });
            setSizeFull();
            setWidth(1100f,Unit.PIXELS);
            setHeight(600f,Unit.PIXELS);
            setModal(true);
            UI.getCurrent().addWindow(this);
        }
    }



}
