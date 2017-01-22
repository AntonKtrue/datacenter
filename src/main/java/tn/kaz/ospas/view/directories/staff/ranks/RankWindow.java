package tn.kaz.ospas.view.directories.staff.ranks;

import com.vaadin.data.fieldgroup.BeanFieldGroup;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.transneft.TransneftRank;
import com.vaadin.ui.*;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by user on 11.01.17.
 */
@SuppressWarnings("serial")
public class RankWindow extends Window {

    private FormLayout layout;
    private BeanFieldGroup<TransneftRank> binder;
    private SimpleJPAContainer<TransneftRank> datasource;
    private CrudButtons<TransneftRank> crudButtons;

    public RankWindow(SimpleJPAContainer<TransneftRank> datasource) {
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
            TransneftRank m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create() {
        setCaption("Новая запись");
        bindingFields(new TransneftRank());
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(TransneftRank m) {
        binder = new BeanFieldGroup<TransneftRank>(TransneftRank.class);
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
