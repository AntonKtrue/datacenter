package tn.kaz.ospas.view.directories;

import tn.kaz.ospas.event.DashboardEventBus;
import tn.kaz.ospas.view.directories.staff.StaffTab;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by user on 10.01.17.
 */
public class DirectoriesView extends TabSheet implements View {

    public DirectoriesView() {

        setSizeFull();
        setStyleName("valo");
        //addStyleName("reports");
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        DashboardEventBus.register(this);

        addTab(new StaffTab());

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }


}
