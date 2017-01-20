package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.funcrequirement.NoticeType;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;

public class FuncRequirementWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<FuncRequirement> binder;
    private SimpleJPAContainer<FuncRequirement> datasource;
    private CrudButtons<NoticeType> crudButtons;

    public FuncRequirementWindow(SimpleJPAContainer<FuncRequirement> datasource
    ) {
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
        bindingFields(new FuncRequirement(structure));
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(FuncRequirement m) {

        FieldFactory fieldFactory = new FieldFactory();
        Label objectName = new Label(m.getStructure().toString());
        layout.addComponent(objectName);

        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(m);
        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("140");
        layout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(50);
        binder.bind(shortDescription,"shortDescription");
        layout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        date.setDateFormat("dd.MM.yyyy");
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

        SimpleJPAContainer<Agreementor> agreementorsDs = new SimpleJPAContainer<Agreementor>(Agreementor.class);





        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
