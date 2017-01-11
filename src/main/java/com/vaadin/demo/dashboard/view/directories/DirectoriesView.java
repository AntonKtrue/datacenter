package com.vaadin.demo.dashboard.view.directories;

import com.vaadin.demo.dashboard.event.DashboardEventBus;
import com.vaadin.demo.dashboard.view.directories.staff.StaffTab;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by user on 10.01.17.
 */
public class DirectoriesView extends TabSheet implements View {

    public DirectoriesView() {
//        setSizeFull();
//        addStyleName("transactions");
//        DashboardEventBus.register(this);
//        addComponent(buildDirectoryList());
        //buildDirectoryList();
        setSizeFull();
        addStyleName("reports");
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
       // setCloseHandler(this);
        DashboardEventBus.register(this);

        VerticalLayout tab1 = new VerticalLayout();
        tab1.setCaption("tab1");
        VerticalLayout tab1content = new VerticalLayout();
        tab1content.setSizeUndefined();
        tab1content.setSpacing(true);
        tab1content.addStyleName("drafts");

        tab1.addComponent(tab1content);
        tab1.setComponentAlignment(tab1content, Alignment.MIDDLE_CENTER);
        Label tab1contentTitle = new Label("Tab 1");
        tab1contentTitle.addStyleName(ValoTheme.LABEL_H1);
        tab1contentTitle.setSizeUndefined();
        tab1content.addComponent(tab1contentTitle);
        tab1content.setComponentAlignment(tab1contentTitle, Alignment.TOP_CENTER);


        VerticalLayout tab2 = new VerticalLayout();
        tab2.setCaption("tab2");
        VerticalLayout tab2content = new VerticalLayout();
        tab2content.setSizeUndefined();
        tab2content.setSpacing(true);
        tab2content.addStyleName("drafts");

        tab2.addComponent(tab2content);
        tab2.setComponentAlignment(tab2content, Alignment.MIDDLE_CENTER);
        Label tab2contentTitle = new Label("Tab 2");
        tab2contentTitle.addStyleName(ValoTheme.LABEL_H1);
        tab2contentTitle.setSizeUndefined();
        tab2content.addComponent(tab2contentTitle);
        tab2content.setComponentAlignment(tab2contentTitle, Alignment.TOP_CENTER);
        addTab(tab2);
        addTab(tab1);
        addTab(new StaffTab());

    }




    private Component buildDirectoryList() {
//        HorizontalLayout directoryListPanel = new HorizontalLayout();
//        Button rankDirecotory = new Button("Должности");
//        directoryListPanel.addComponent(rankDirecotory);
//        return directoryListPanel;

       VerticalLayout content = new VerticalLayout();
//        content.setSizeFull();
//        HorizontalLayout titleBar = new HorizontalLayout();
//        VerticalLayout bodyBar = new VerticalLayout();
//
//        titleBar.setSizeUndefined();
//        titleBar.setMargin(new MarginInfo(true, true, false, true));
//        Label title1 = new Label("title1");
//        titleBar.addComponent(title1);
//        Label title2 = new Label("title2");
//        titleBar.addComponent(title2);
//        bodyBar.setSizeFull();
//        Label body1 = new Label("body1");
//        bodyBar.addComponent(body1);
//        Label body2 = new Label("body2");
//        bodyBar.addComponent(body2);
//        Label body3 = new Label("body3");
//        bodyBar.addComponent(body3);
//        content.addComponent(titleBar);
//        content.addComponent(bodyBar);
//
//        content.setExpandRatio(bodyBar, 1.0f);
//        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        return content;
    }

    @Override
    public void detach() {
        super.detach();
      //  DashboardEventBus.register(this);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }


}
