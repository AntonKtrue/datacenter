package tn.kaz.ospas.view.directories.funcrequirement.notice;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.NoticeType;
import tn.kaz.ospas.view.CrudButtons;

public class NoticeWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<NoticeType> binder;
    private SimpleJPAContainer<NoticeType> datasource;
    private CrudButtons<NoticeType> crudButtons;

    public NoticeWindow(SimpleJPAContainer<NoticeType> datasource) {
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
            NoticeType m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create() {
        setCaption("Новая запись");
        bindingFields(new NoticeType());
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(NoticeType m) {
        binder = new BeanFieldGroup<NoticeType>(NoticeType.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Полное название", "name");
        field.setWidth("140");
        layout.addComponent(field);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
