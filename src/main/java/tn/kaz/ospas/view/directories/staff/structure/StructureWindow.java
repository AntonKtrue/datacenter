package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;

import tn.kaz.ospas.data.HierarchicalStructureContainer;
import tn.kaz.ospas.model.transneft.StructureType;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 13.01.2017.
 */
@SuppressWarnings("serial")
public class StructureWindow extends Window {

    private FormLayout layout;
    private BeanFieldGroup<TransneftStructure> binder;
    private CrudButtons<TransneftStructure> crudButtons;
    private HierarchicalStructureContainer datasource;

    public StructureWindow(HierarchicalStructureContainer datasource) {
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
            TransneftStructure m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    public void create(TransneftStructure selectedStructure) {
        setCaption("Новая запись");
        TransneftStructure ts = new TransneftStructure(selectedStructure);
        bindingFields(ts);
        UI.getCurrent().addWindow(this);
    }

    private void bindingFields(TransneftStructure m) {
        binder = new BeanFieldGroup<TransneftStructure>(TransneftStructure.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Полное название", "name");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Краткое название", "shortName");
        field.setWidth("250");
        layout.addComponent(field);
        if(m.getType() == StructureType.OST
                || m.getType() == StructureType.UMN
                || m.getType() == StructureType.OBJ)  {
            field = binder.buildAndBind("Шифр", "code");
            field.setWidth("80");
            layout.addComponent(field);

        }
        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }

}
