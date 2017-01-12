package com.vaadin.demo.dashboard.view.directories.staff.department;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.demo.dashboard.data.HierarchicalDepartmentContainer;
import com.vaadin.demo.dashboard.model.staff.Department;
import com.vaadin.demo.dashboard.model.staff.Person;
import com.vaadin.ui.*;

import java.text.Bidi;

/**
 * Created by Anton on 12.01.2017.
 */
public class DepartmentView extends HorizontalSplitPanel implements ComponentContainer {
    private static final String PERSISTENCE_UNIT = "appVaadinUnit";
    private Tree groupTree;
    private Table personTable;

    private TextField searchField;

    private Button newButton;
    private Button deleteButton;
    private Button editButton;

    private JPAContainer<Department> departments;
    private JPAContainer<Person> persons;

    private Department departmentFilter;
    private String textFilter;

    public DepartmentView() {
        departments = new HierarchicalDepartmentContainer();
        persons = JPAContainerFactory.make(Person.class, PERSISTENCE_UNIT);

    }

    private void buildMainArea() {
        newButton = new Button("Добавить");
        newButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                final BeanItem<Person> newPersonItem;
            }
        });
        personTable = new Table(null, persons);
    }
}
