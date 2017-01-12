package com.vaadin.demo.dashboard.view.directories.staff;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.demo.dashboard.data.HierarchicalDepartmentContainer;
import com.vaadin.demo.dashboard.model.staff.Department;
import com.vaadin.demo.dashboard.view.directories.staff.department.DepartmentTree;
import com.vaadin.demo.dashboard.view.directories.staff.ranks.RankTab;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Anton on 11.01.2017.
 */
public class StaffTab extends TabSheet {

    public StaffTab() {
        setSizeFull();
        addStyleName("reports");
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setCaption("Сотрудники");

        VerticalLayout ranks = new VerticalLayout();
        ranks.setCaption("Должности");
        VerticalLayout ranksContent = new VerticalLayout();
        ranksContent.setSizeFull();
        ranksContent.setSpacing(true);
        ranksContent.addStyleName("drafts");

        ranks.addComponent(ranksContent);
        ranks.setComponentAlignment(ranksContent, Alignment.MIDDLE_CENTER);
        Label ranksContentTitle = new Label("Должности");
        ranksContentTitle.addStyleName(ValoTheme.LABEL_H1);
        ranksContentTitle.setSizeUndefined();
        ranksContent.addComponent(ranksContentTitle);
        ranksContent.setComponentAlignment(ranksContentTitle, Alignment.TOP_CENTER);
        ranksContent.addComponent(new RankTab());
        addTab(ranks);

        VerticalLayout departaments = new VerticalLayout();
        departaments.setCaption("Отделы");
        VerticalLayout depContent = new VerticalLayout();
        depContent.setSizeUndefined();
        depContent.setSpacing(true);
        depContent.addStyleName("drafts");
        departaments.addComponent(depContent);
        departaments.setComponentAlignment(depContent, Alignment.MIDDLE_CENTER);
        Label depContentTitle = new Label("Отделы");
        depContentTitle.addStyleName(ValoTheme.LABEL_H1);
        depContentTitle.setSizeUndefined();
        depContent.addComponent(depContentTitle);
        depContent.setComponentAlignment(depContentTitle, Alignment.TOP_CENTER);
        JPAContainer<Department> departments = new HierarchicalDepartmentContainer();
//        JPAContainer<Department> departments = JPAContainerFactory.make(
//                Department.class, "appVaadinUnit"
//        );
//        departaments.setP

        Tree tree = new Tree(null, departments);
        tree.setItemCaptionPropertyId("name");
        tree.setImmediate(true);
        tree.setSelectable(true);
        depContent.addComponent(tree);
        //depContent.addComponent(new DepartmentTree(new HierarchicalDepartmentContainer(), "name"));
        addTab(departaments);

        VerticalLayout employee = new VerticalLayout();
        employee.setCaption("Персонал");
        VerticalLayout emloyeeContent = new VerticalLayout();
        emloyeeContent.setSizeUndefined();
        emloyeeContent.setSpacing(true);
        emloyeeContent.addStyleName("drafts");
        employee.addComponent(emloyeeContent);
        employee.setComponentAlignment(emloyeeContent, Alignment.MIDDLE_CENTER);
        Label employeeContentTitle = new Label("Персонал");
        employeeContentTitle.addStyleName(ValoTheme.LABEL_H1);
        employeeContentTitle.setSizeUndefined();
        emloyeeContent.addComponent(employeeContentTitle);
        emloyeeContent.setComponentAlignment(employeeContentTitle, Alignment.TOP_CENTER);
        addTab(employee);
    }




}
