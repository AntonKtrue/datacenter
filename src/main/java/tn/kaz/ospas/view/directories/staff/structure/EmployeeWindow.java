package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.fieldgroup.BeanFieldGroup;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.transneft.*;
import tn.kaz.ospas.view.CrudButtons;
import tn.kaz.ospas.view.GuiHelper;

/**
 * Created by user on 18.01.17.
 */
public class EmployeeWindow extends Window {
    private FormLayout layout;

    private BeanFieldGroup<TransneftEmployee> binder;
    private CrudButtons<TransneftEmployee> crudButtons;
    private SimpleJPAContainer<TransneftRank> ranks;
    private SimpleJPAContainer<TransneftEmployee> datasource;

    public EmployeeWindow(SimpleJPAContainer<TransneftEmployee> datasource, SimpleJPAContainer<TransneftRank> ranks) {
        this.datasource = datasource;
        this.ranks = ranks;
        init();
        setModal(true);
    }


    protected void init() {
        layout = new FormLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        setContent(layout);
        setHeight("470");
        setWidth("600");
    }

    public void edit(Integer id) {
        try {
            setCaption("Редактирование структуры");
            TransneftEmployee m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create(TransneftDepartment department) {
        setCaption("Новая запись");
        TransneftEmployee ts = new TransneftEmployee(department);
        bindingFields(ts);
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(TransneftEmployee m) {
        binder = new BeanFieldGroup<TransneftEmployee>(TransneftEmployee.class);
        binder.setItemDataSource(m);
//        Field<?> field = null;
//        field = binder.buildAndBind("Фамилия", "firstName");
//        field.setWidth("250");
//        layout.addComponent(field);
//
//        field = binder.buildAndBind("Фамилия", "lastName");
//        field.setWidth("250");
//        layout.addComponent(field);
//
//        field = binder.buildAndBind("Отчество", "patroName");
//        field.setWidth("250");
//        layout.addComponent(field);

        TextField firstName, lastName, patroName;
        lastName = GuiHelper.makeTextField("Фамилия",TransneftEmployee.class,"lastName");
        firstName = GuiHelper.makeTextField("Имя",TransneftEmployee.class,"firstName");
        patroName = GuiHelper.makeTextField("Отчество",TransneftEmployee.class,"patroName");
        lastName.setRequired(true);
        firstName.setRequired(true);
        binder.bind(lastName,"lastName");
        binder.bind(firstName,"firstName");
        binder.bind(patroName,"patroName");


        ComboBox rank = new ComboBox("Должность", ranks);
        rank.setContainerDataSource(ranks);
        rank.setItemCaptionPropertyId("name");
        rank.setImmediate(true);
        rank.setConverter(new SingleSelectConverter(rank));
        rank.setRequired(true);
        binder.bind(rank, "rank");
        layout.addComponents(firstName, lastName, patroName, rank);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
