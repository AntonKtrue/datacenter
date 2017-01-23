package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.transneft.TransneftRank;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 23.01.2017.
 */
public class CrudWindow<T extends Identity> extends Window {

    private FormLayout layout;
    private BeanFieldGroup<T> binder;
    private SimpleJPAContainer<T> datasource;
    private CrudButtons<T> crudButtons;
    private Class<T> clazz;

    public CrudWindow(Class<T> clazz, SimpleJPAContainer<T> datasource) {
        this.datasource = datasource;
        this.clazz = clazz;
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
            T m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create() throws IllegalAccessException, InstantiationException {
        setCaption("Новая запись");
        bindingFields(clazz.newInstance());
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(T m) {
        binder = new BeanFieldGroup<T>(clazz);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Полное название", "name");
        field.setWidth("250");
        field.setRequired(true);
        layout.addComponent(field);

        field = binder.buildAndBind("Краткое название", "shortName");
        field.setWidth("250");
        field.setRequired(true);
        layout.addComponent(field);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }

}
