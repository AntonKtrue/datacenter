package com.vaadin.demo.dashboard.view.directories.staff.structure;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;

/**
 * Created by Anton on 13.01.2017.
 */
@SuppressWarnings("serial")
public class StructureWindow extends Window implements Button.ClickListener {

    private FormLayout layout;
    private BeanFieldGroup<TransneftStructure> binder;
    private HorizontalLayout buttons;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }
}
