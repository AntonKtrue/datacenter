package tn.kaz.ospas.view.directories.funcrequirement.executors;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.executors.ExecutorType;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 19.01.2017.
 */
@SuppressWarnings("serial")
public class ExecutorsTypesWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<ExecutorType> binder;
    private SimpleJPAContainer<ExecutorType> datasource;
    private CrudButtons<ExecutorType> crudButtons;

    public ExecutorsTypesWindow(SimpleJPAContainer<ExecutorType> datasource) {
        this.datasource = datasource;
        init();
        setModal(true);
    }

    private void init() {
        layout = new FormLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        setContent(layout);
        setHeight("370");
        setWidth("400");
    }


    public void edit(Integer id) {
        try {
            setCaption("Редактирование должности");
            ExecutorType m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create() {
        setCaption("Новая запись");
        bindingFields(new ExecutorType());
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(ExecutorType m) {
        binder = new BeanFieldGroup<ExecutorType>(ExecutorType.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Полное название", "name");
        field.setWidth("140");
        layout.addComponent(field);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
