package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.FuncRequirementJPAContainer;
import tn.kaz.ospas.data.NoticeJPAContainer;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.funcrequirement.NoticeType;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirementWindow extends Window {
    private FormLayout layout;
    private BeanFieldGroup<FuncRequirement> binder;
    private FuncRequirementJPAContainer datasource;
    private CrudButtons<NoticeType> crudButtons;

    public FuncRequirementWindow(FuncRequirementJPAContainer datasource) {
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
            FuncRequirement m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create() {
        setCaption("Новая запись");
        bindingFields(new FuncRequirement());
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(FuncRequirement m) {
        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(m);
        Field<?> field = null;


        field = binder.buildAndBind("Номер", "number");
        field.setWidth("140");
        layout.addComponent(field);



        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }
}
