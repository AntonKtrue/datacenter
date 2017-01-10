package com.vaadin.demo.dashboard.view.directories;

import com.vaadin.demo.dashboard.event.DashboardEventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by user on 10.01.17.
 */
public class DirectoriesView extends VerticalLayout implements View {

    public DirectoriesView() {
        setSizeFull();
        addStyleName("transactions");
        DashboardEventBus.register(this);
        addComponent(buildDirectoryList());

    }

    private Component buildDirectoryList() {
        HorizontalLayout directoryListPanel = new HorizontalLayout();
        Button rankDirecotory = new Button("Должности");
        directoryListPanel.addComponent(rankDirecotory);
        return directoryListPanel;
    }

    @Override
    public void detach() {
        super.detach();
        DashboardEventBus.register(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }


}
