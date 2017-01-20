package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.ui.Table;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirementTable extends Table {
    public FuncRequirementTable(SimpleJPAContainer<FuncRequirement> datasource) {
        setContainerDataSource(datasource);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id",
                "number",
                "structure",
                "acceptor",
                "date",
                "executors"
                });
        setColumnHeaders(new String[]{"#",
                "Номер",
                "Объект",
                "Утверждающий",
                "Дата",
                "Исполнители"});
    }
}
