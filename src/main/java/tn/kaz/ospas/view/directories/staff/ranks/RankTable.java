package tn.kaz.ospas.view.directories.staff.ranks;

import com.vaadin.ui.Table;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.transneft.TransneftRank;

/**
 * Created by user on 11.01.17.
 */
@SuppressWarnings("serial")
public class RankTable extends Table {

    public RankTable(SimpleJPAContainer<TransneftRank> datasource) {
        setContainerDataSource(datasource);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "name", "shortName"});
        setColumnHeaders(new String[]{"#","Полное название", "Короткое название"});
    }
}
