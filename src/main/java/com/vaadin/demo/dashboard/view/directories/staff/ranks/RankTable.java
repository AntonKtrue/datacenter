package com.vaadin.demo.dashboard.view.directories.staff.ranks;

import com.vaadin.demo.dashboard.data.RankJPAContainer;
import com.vaadin.ui.Table;

/**
 * Created by user on 11.01.17.
 */
@SuppressWarnings("serial")
public class RankTable extends Table {


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
