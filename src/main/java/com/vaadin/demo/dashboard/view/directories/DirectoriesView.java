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

        setSizeFull();
        addStyleName("reports");
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        DashboardEventBus.register(this);

        addTab(new StaffTab());

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }


}
