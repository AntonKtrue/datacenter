package tn.kaz.ospas.view.directories.funcrequirement.notice;

import com.vaadin.ui.Table;
import tn.kaz.ospas.data.NoticeJPAContainer;

/**
 * Created by Anton on 19.01.2017.
 */
public class NoticeTable extends Table {
    public NoticeTable(NoticeJPAContainer datasource) {
        setContainerDataSource(datasource);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "name"});
        setColumnHeaders(new String[]{"#","Полное название"});
    }
}
