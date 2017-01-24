package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;

import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.funcrequirement.NoticeType;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;
import tn.kaz.ospas.view.funcrequirements.components.AgreementorWindow;
import tn.kaz.ospas.view.funcrequirements.components.OneToManyField;

import javax.persistence.Query;
import java.io.File;
import java.util.Date;
import java.util.List;

public class FuncRequirementWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<FuncRequirement> binder;
    private SimpleJPAContainer<FuncRequirement> datasource;
    private CrudButtons<NoticeType> crudButtons;

    public FuncRequirementWindow(SimpleJPAContainer<FuncRequirement> datasource ) {
        this.datasource = datasource;
        init();
        setModal(true);
    }

    private void init() {
        layout = new FormLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        setContent(layout);
        setHeight("600");
        setWidth("1200");
    }




    public void edit(Integer id) {
        try {
            setCaption("Редактирование должности");
            FuncRequirement m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create(TransneftStructure structure) {
        setCaption("Новая запись");
        FuncRequirement funcRequirement = new FuncRequirement(structure);
        Query query = datasource.getEntityProvider().getEntityManager().createNamedQuery("Number.empty");
        List<Long> result  = query.getResultList();
        funcRequirement.setNumber(result.size() > 0 ? result.get(0) : 1);
        funcRequirement.setDate(new Date());
        bindingFields(funcRequirement);
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(final FuncRequirement funcRequirement) {

        //FieldFactory fieldFactory = new FieldFactory();
        Label objectName = new Label(funcRequirement.getStructure().toString());
        layout.addComponent(objectName);

        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);
        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("140");
        layout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(50);
        shortDescription.setNullRepresentation("");
        binder.bind(shortDescription,"shortDescription");
        layout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        //date.setDateFormat("dd.MM.yyyy");
        date.setRequired(true);
        date.setValue(new Date());
        binder.bind(date,"date" );
        layout.addComponent(date);

//        PopupDateField developmentDate = new PopupDateField("Срок разработки:");
//        developmentDate.setDateFormat("dd.MM.yyyy");
//        binder.bind(developmentDate,"developmentDate" );
//        layout.addComponent(developmentDate);
//        PopupDateField standTestDate = new PopupDateField("Срок испытания:");
//        standTestDate.setDateFormat("dd.MM.yyyy");
//        binder.bind(standTestDate,"standTestDate" );
//        layout.addComponent(standTestDate);
//        PopupDateField implementationDate = new PopupDateField("Срок внедрения:");
//        implementationDate.setDateFormat("dd.MM.yyyy");
//        binder.bind(implementationDate,"implementationDate" );
//        layout.addComponent(implementationDate);
//        if(funcRequirement.getFrFilePath() != null) {
//            Link frFileLink = new Link("ФТ", new FileResource(new File(funcRequirement.getFrFilePath())));
//            frFileLink.setTargetName("_blank");
//            layout.addComponent(frFileLink);
//        }

//        SimpleJPAContainer<Agreementor> agreementorsDs = new SimpleJPAContainer<Agreementor>(Agreementor.class);
//        final JPAContainer<Agreementor> agreementorsDs = JPAContainerFactory.make(Agreementor.class, Config.JPA_UNIT);
//        agreementorsDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
//        agreementorsDs.applyFilters();
//        final OneToManyField<Agreementor> agreementors = new OneToManyField<Agreementor>(
//                "Согласующие",
//                binder,agreementorsDs ,
//                new Object[]{"id","sequence", "employee", "rank", "department"},
//                new String[]{"#","Порядок","Сотрудник","Должность","Отдел"},
//                "agreementors"
//        );
//        agreementors.addListenerToAddButton(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
//                window.create();
//            }
//        });
//        agreementors.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
//            @Override
//            public void itemClick(ItemClickEvent event) {
//                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
//                window.edit(Integer.valueOf(event.getItemId().toString()));
//            }
//        });
//
//        layout.addComponent(agreementors);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
