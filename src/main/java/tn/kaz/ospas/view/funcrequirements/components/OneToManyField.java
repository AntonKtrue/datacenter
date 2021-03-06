package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;

import tn.kaz.ospas.model.Identity;

import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 23.01.2017.
 */
public class OneToManyField<T extends Identity> extends VerticalLayout {

    private String caption;
    private BeanFieldGroup<FuncRequirement> binder;
    private JPAContainer<T> datasource;
    private Object[] columns;
    private String[] headers;
    private String property;
    private CrudButtons<T> crudButtons;
    private Button addButon, editButton, deleteButton, upButton;
    private FuncRequirement funcRequirement;
    private Table table;
    private T selectedObject;

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

    public Table getTable() {
        return table;
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
        deleteButton.setEnabled(false);
        upButton.setVisible(false);
        HorizontalLayout buttons = new HorizontalLayout(addButon, editButton, deleteButton, upButton);
        layout.addComponent(buttons);
        table = new Table();

        table.setContainerDataSource(datasource);
        table.setSelectable(true);
        table.setSizeFull();
        table.setVisibleColumns(columns);
        table.setColumnHeaders(headers);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedObject = datasource.getItem(event.getItemId()).getEntity();
                deleteButton.setEnabled(true);
            }
        });

        binder.bind(table,property);
        layout.addComponent(table);
        addComponent(layout);
    }



    public T getSelectedObject() {
        return selectedObject;
    }

    public void removeSelectedItem() {
        datasource.removeItem(selectedObject.getId());
        selectedObject = null;
        deleteButton.setEnabled(false);
    }

    public void addListenerToAddButton(Button.ClickListener listener) {
        addButon.addClickListener(listener);
        addButon.setVisible(true);
    }
    public void addListenerToEditButton(Button.ClickListener listener) {
        editButton.addClickListener(listener);
        editButton.setVisible(true);
    }
    public void addListenerToDeleteButton(Button.ClickListener listener) {
        deleteButton.addClickListener(listener);
        deleteButton.setVisible(true);
    }
    public void addListenerToUpButton(Button.ClickListener listener) {
        upButton.addClickListener(listener);
        upButton.setVisible(true);
    }


}
