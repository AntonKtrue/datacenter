package tn.kaz.ospas.view.directories.funcrequirement.notice;

import com.vaadin.ui.Table;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.NoticeType;

/**
 * Created by Anton on 19.01.2017.
 */
public class NoticeTable extends Table {
    public NoticeTable(SimpleJPAContainer<NoticeType> datasource) {
        setContainerDataSource(datasource);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "name"});
        setColumnHeaders(new String[]{"#","Полное название"});
    }
}
