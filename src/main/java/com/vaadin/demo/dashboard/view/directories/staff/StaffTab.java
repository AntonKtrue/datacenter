package com.vaadin.demo.dashboard.view.directories.staff;

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

        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setCaption("Сотрудники");

        addTab("Должности", Alignment.MIDDLE_CENTER, new RankTab());
        addTab("Структура организации",Alignment.TOP_LEFT, new StructureTab());


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
