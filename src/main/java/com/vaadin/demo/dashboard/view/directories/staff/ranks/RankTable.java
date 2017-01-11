package com.vaadin.demo.dashboard.view.directories.staff.ranks;

import com.vaadin.demo.dashboard.data.RankJPAContainer;
import com.vaadin.ui.Table;

/**
 * Created by user on 11.01.17.
 */
public class RankTable extends Table {
    private static final long serialVersionUID = 7224804420418157408L;

    public RankTable(RankJPAContainer rankContainer) {
        setContainerDataSource(rankContainer);
        setWidth("100%");

        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "name", "shortName"});
        setColumnHeaders(new String[]{"#","Полное название", "Короткое название"});
    }


}
