package com.vaadin.demo.dashboard.view.directories.staff.structure;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTab extends VerticalLayout {

    public StructureTab() {
        HorizontalLayout wrapper = new HorizontalLayout();
        wrapper.setWidth("100%");
        setSizeFull();
        wrapper.setSizeFull();

        Panel treeWrapper = new Panel();
        treeWrapper.setHeight("100%");
        wrapper.addComponent(treeWrapper);
        treeWrapper.setContent(new StructureTree());

        Button addButton = new Button("Добавить");
        Button editButton = new Button("Редактировать");
        Button delButton = new Button("Удалить");
        VerticalLayout buttons = new VerticalLayout(addButton, editButton, delButton);
        wrapper.addComponent(buttons);
        addComponent(wrapper);

    }
}
