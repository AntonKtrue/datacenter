package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.transneft.TransneftRank;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 23.01.2017.
 */
public class OneToManyField<T extends Identity> extends Panel {

    private String caption;
    private BeanFieldGroup<FuncRequirement> binder;
    private JPAContainer<T> datasource;
    private Object[] columns;
    private String[] headers;
    private String property;
    private CrudButtons<T> crudButtons;
    private Button addButon, editButton, deleteButton, upButton;
    private FuncRequirement funcRequirement;

    public OneToManyField(String caption, BeanFieldGroup<FuncRequirement> binder, FuncRequirement funcRequirement, Object[] columns, String[] headers, String property) {
        this.caption = caption;
        this.binder = binder;
        this.funcRequirement = funcRequirement;
        this.columns = columns;
        this.headers = headers;
        this.property = property;

        init();
        setImmediate(true);
        setWidth(800f,Unit.PIXELS);
        setHeight(300f,Unit.PIXELS);
    }

    public OneToManyField(String caption, BeanFieldGroup<FuncRequirement> binder, JPAContainer<T> datasource, Object[] columns, String[] headers, String property) {
        this.caption = caption;
        this.binder = binder;
        this.datasource = datasource;
        this.columns = columns;
        this.headers = headers;
        this.property = property;



        init();
        setImmediate(true);
        setWidth(800f,Unit.PIXELS);
        setHeight(300f,Unit.PIXELS);
    }

    private void init() {
        VerticalLayout layout = new VerticalLayout();
        setCaption(caption);
        addButon = new Button("Добавить");
        editButton = new Button("Изменить");
        deleteButton = new Button("Удалить");
        upButton = new Button("Поднять выше");
        addButon.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        upButton.setVisible(false);
        HorizontalLayout buttons = new HorizontalLayout(addButon, editButton, deleteButton, upButton);
        layout.addComponent(buttons);
        Table table = new Table();
        table.setContainerDataSource(datasource);

        table.setSelectable(true);

        table.setSizeFull();
        table.setVisibleColumns(columns);
        table.setColumnHeaders(headers);
        binder.bind(table,property);
        layout.addComponent(table);
        setContent(layout);
    }

    public void addListenerToAddButton(Button.ClickListener listener) {
        addButon.addClickListener(listener);
        addButon.setVisible(true);
    }
    public void addListenerToEditButton(Button.ClickListener listener) {
        editButton.addClickListener(listener);
    }
    public void addListenerToDeleteButton(Button.ClickListener listener) {
        deleteButton.addClickListener(listener);
    }
    public void addListenerToUpButton(Button.ClickListener listener) {
        upButton.addClickListener(listener);
    }


}
