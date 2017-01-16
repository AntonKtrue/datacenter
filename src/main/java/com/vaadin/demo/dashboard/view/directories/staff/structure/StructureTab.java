package com.vaadin.demo.dashboard.view.directories.staff.structure;

import com.vaadin.demo.dashboard.data.HierarchicalStructureContainer;
import com.vaadin.demo.dashboard.model.transneft.StructureType;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTab extends VerticalLayout {
    private Tree tree;
    private StructureType selectedItem;
    private TransneftStructure parent;



    public StructureTab() {
//        HorizontalLayout wrapper = new HorizontalLayout();
//        wrapper.setWidth("100%");
//        setSizeFull();
//        wrapper.setSizeFull();
//
//        Panel treeWrapper = new Panel();
//        treeWrapper.setHeight("100%");
//        wrapper.addComponent(treeWrapper);
//        treeWrapper.setContent(new StructureTree());
//
//        Button addButton = new Button("Добавить");
//        Button editButton = new Button("Редактировать");
//        Button delButton = new Button("Удалить");
//        VerticalLayout buttons = new VerticalLayout(addButton, editButton, delButton);
//        wrapper.addComponent(buttons);
//        addComponent(wrapper);
        final HierarchicalStructureContainer datasource = new HierarchicalStructureContainer();
        tree = new StructureTree(datasource);
        tree.addItemClickListener(
                new ItemClickEvent.ItemClickListener() {
                    @Override
                    public void itemClick(ItemClickEvent event) {
                       // StructureWindow window = new StructureWindow(datasource);
                       // window.edit(Integer.valueOf(event.getItemId().toString()));
                        //Notification.show(event.getItem().getItemProperty("type").toString());
                        selectedItem = (StructureType) event.getItem().getItemProperty("type").getValue();
                        parent = datasource.getItem(event.getItemId()).getEntity();
                     //   Notification.show(selectedItem.toString() + " " + parent);
                    }
                }
        );
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);

        VerticalLayout vertical = putAllOnVertical(tree,
                buildBarButtons(datasource));
        content.addComponent(vertical);
        content.setComponentAlignment(vertical, Alignment.MIDDLE_CENTER);

        addErrorHandle(content);
    }
    private VerticalLayout putAllOnVertical(Component... components) {
        VerticalLayout vertical = new VerticalLayout();
        for (Component c: components) {
            if (c != null) {
                vertical.addComponent(c);
                vertical.setComponentAlignment(c, Alignment.MIDDLE_CENTER);
            }
        }
        return vertical;
    }

    private HorizontalLayout buildBarButtons(final HierarchicalStructureContainer datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                StructureWindow window = new StructureWindow(datasource);
                window.create(selectedItem, parent);
            }
        });

        Button refreshButton = new Button("Обновить");
        refreshButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                datasource.refresh();
            }
        });

        Button[] buttons = {addButton, refreshButton};
        HorizontalLayout barButton = new HorizontalLayout();
        barButton.setHeight("50");

        for (Button b: buttons) {
            b.setStyleName(Runo.BUTTON_BIG);
            barButton.addComponent(b);
            barButton.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
        }
        return barButton;
    }
    private void addErrorHandle(final VerticalLayout content) {
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "Произошла ошибка!:\n";
                for (Throwable t = event.getThrowable(); t != null;
                     t = t.getCause())
                    if (t.getCause() == null)
                        cause += t.getClass().getName() + "\n";

                Notification.show(cause, Notification.Type.ERROR_MESSAGE);
                doDefault(event);
            }
        });
    }
}
