package com.vaadin.demo.dashboard.view.directories.staff;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.HierarchicalContainer;
//import com.vaadin.demo.dashboard.data.HierarchicalDepartmentContainer;
//import com.vaadin.demo.dashboard.model.staff.Department;
import com.vaadin.demo.dashboard.view.directories.staff.department.DepartmentTree;
import com.vaadin.demo.dashboard.view.directories.staff.ranks.RankTab;
import com.vaadin.demo.dashboard.view.directories.staff.structure.StructureTab;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Anton on 11.01.2017.
 */
public class StaffTab extends TabSheet {


    private Tab addTab(String caption, Alignment contentAlignment, Component contentComponent) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setCaption(caption);
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.setSpacing(true);
        wrapper.addComponent(content);
        wrapper.setComponentAlignment(content, contentAlignment);
        Label title = new Label(caption);
        title.addStyleName(ValoTheme.LABEL_H1);
        title.setSizeUndefined();
        content.addComponent(title);
        content.setComponentAlignment(title, Alignment.TOP_CENTER);
        content.addComponent(contentComponent);
        return addTab(wrapper);
    }

    public StaffTab() {
        setSizeFull();
        //addStyleName("reports");
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setCaption("Сотрудники");

        addTab("Должности", Alignment.MIDDLE_CENTER, new RankTab());
        addTab("Структура организации",Alignment.MIDDLE_CENTER, new StructureTab());




//        VerticalLayout structure = new VerticalLayout();
//        structure.setCaption("Структура организации");
//        VerticalLayout strcutContent = new VerticalLayout();
//        strcutContent.setSizeFull();
//        strcutContent.setSpacing(true);
//        strcutContent.addStyleName("drafts");
//        structure.addComponent(strcutContent);
//        structure.setComponentAlignment(strcutContent, Alignment.MIDDLE_CENTER);
//        Label strcutContentTitle = new Label("Структура организации");
//        strcutContentTitle.addStyleName(ValoTheme.LABEL_H1);
//        strcutContentTitle.setSizeUndefined();
//        strcutContent.addComponent(strcutContentTitle);
//        strcutContent.setComponentAlignment(strcutContentTitle, Alignment.TOP_CENTER);
//
//        HorizontalLayout treeWrap = new HorizontalLayout();
//        treeWrap.setSizeUndefined();
//        strcutContent.addComponent(treeWrap);
//        strcutContent.setComponentAlignment(treeWrap, Alignment.TOP_LEFT);
//        Tree deps = new ExampleTree();
//        deps.setSizeFull();
//        treeWrap.addComponent(deps);
//        addTab(structure);

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
