package tn.kaz.ospas.view;

import tn.kaz.ospas.view.dashboard.DashboardView;
import tn.kaz.ospas.view.directories.DirectoriesView;

import tn.kaz.ospas.view.funcrequirements.FuncRequirementView;
import tn.kaz.ospas.view.reports.ReportsView;
import tn.kaz.ospas.view.schedule.ScheduleView;
import tn.kaz.ospas.view.transactions.TransactionsView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum DashboardViewType {
    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true),
    TRANSACTIONS("transactions", TransactionsView.class, FontAwesome.TABLE, false),
    REPORTS("reports", ReportsView.class, FontAwesome.FILE_TEXT_O, true),
    SCHEDULE("schedule", ScheduleView.class, FontAwesome.CALENDAR_O, false),
    DIRECTORIES("справочники", DirectoriesView.class, FontAwesome.BOOK, false),
    FUNCREQUIREMENTS("функ. требования",FuncRequirementView.class, FontAwesome.INDUSTRY, true)
    ;


    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private DashboardViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
