package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.HierarchicalDepartmentContainer;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;

public class DepartmentWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<TransneftDepartment> binder;
    private CrudButtons crudButtons;
    private HierarchicalDepartmentContainer datasource;

    public DepartmentWindow(HierarchicalDepartmentContainer datasource) {
        this.datasource = datasource;
        itit();
        setModal(true);
    }

    protected void itit() {
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
            TransneftDepartment m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    public void create(TransneftDepartment parent, TransneftStructure structure) {
        setCaption("Новая запись");
        bindingFields(new TransneftDepartment(parent, structure));
        UI.getCurrent().addWindow(this);
    }

    private void bindingFields(TransneftDepartment m) {
        binder = new BeanFieldGroup<TransneftDepartment>(TransneftDepartment.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Полное название", "name");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Краткое название", "shortName");
        field.setWidth("250");
        layout.addComponent(field);
        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }

}
