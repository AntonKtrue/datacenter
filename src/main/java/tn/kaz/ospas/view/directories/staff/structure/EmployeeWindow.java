package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.data.EmployeeJPAContainer;
import tn.kaz.ospas.data.RankJPAContainer;
import tn.kaz.ospas.model.transneft.*;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by user on 18.01.17.
 */
public class EmployeeWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<TransneftEmployee> binder;
    private CrudButtons<TransneftEmployee> crudButtons;
    private RankJPAContainer ranks;
    private EmployeeJPAContainer datasource;

    public EmployeeWindow(EmployeeJPAContainer datasource, RankJPAContainer ranks) {
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
        Field<?> field = null;
        field = binder.buildAndBind("Имя", "firstName");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Фамилия", "lastName");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Отчество", "patroName");
        field.setWidth("250");
        layout.addComponent(field);

        ComboBox rank = new ComboBox("Должность", ranks);
        rank.setContainerDataSource(ranks);
        rank.setItemCaptionPropertyId("name");
        rank.setImmediate(true);
        rank.setConverter(new SingleSelectConverter(rank));
        binder.bind(rank, "rank");
        layout.addComponent(rank);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
