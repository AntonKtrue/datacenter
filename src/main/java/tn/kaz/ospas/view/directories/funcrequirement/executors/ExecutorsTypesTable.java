package tn.kaz.ospas.view.directories.funcrequirement.executors;

import com.vaadin.ui.Table;
import tn.kaz.ospas.data.ExecutorsTypeJPAContainer;
import tn.kaz.ospas.data.RankJPAContainer;

/**
 * Created by Anton on 19.01.2017.
 */
public class ExecutorsTypesTable extends Table {
    public ExecutorsTypesTable(ExecutorsTypeJPAContainer rankContainer) {
        setContainerDataSource(rankContainer);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "name"});
        setColumnHeaders(new String[]{"#","Полное название"});
    }
}
