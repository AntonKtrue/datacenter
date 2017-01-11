package com.vaadin.demo.dashboard.view.directories.staff;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
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
        ranksContent.setSizeUndefined();
        ranksContent.setSpacing(true);
        ranksContent.addStyleName("drafts");
        ranks.addComponent(ranksContent);
        ranks.setComponentAlignment(ranksContent, Alignment.MIDDLE_CENTER);
        Label ranksContentTitle = new Label("Должности");
        ranksContentTitle.addStyleName(ValoTheme.LABEL_H1);
        ranksContentTitle.setSizeUndefined();
        ranksContent.addComponent(ranksContentTitle);
        ranksContent.setComponentAlignment(ranksContentTitle, Alignment.TOP_CENTER);
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
        addTab(departaments);

        VerticalLayout personal = new VerticalLayout();
        personal.setCaption("Персонал");
        VerticalLayout persContent = new VerticalLayout();
        persContent.setSizeUndefined();
        persContent.setSpacing(true);
        persContent.addStyleName("drafts");
        personal.addComponent(persContent);
        personal.setComponentAlignment(persContent, Alignment.MIDDLE_CENTER);
        Label persContentTitle = new Label("Персонал");
        persContentTitle.addStyleName(ValoTheme.LABEL_H1);
        persContentTitle.setSizeUndefined();
        persContent.addComponent(persContentTitle);
        persContent.setComponentAlignment(persContentTitle, Alignment.TOP_CENTER);
        addTab(personal);
    }




}
