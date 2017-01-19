package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import tn.kaz.ospas.event.DashboardEventBus;
import tn.kaz.ospas.view.GuiHelper;
import tn.kaz.ospas.view.directories.staff.StaffTab;

/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirements extends TabSheet implements View {

    public FuncRequirements() {
        setSizeFull();
        setStyleName("valo");

        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        DashboardEventBus.register(this);
        addTab(GuiHelper.makeTabContent("Функциональные требования", Alignment.TOP_LEFT, new FReqTab()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
