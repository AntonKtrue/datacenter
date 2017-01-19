package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.ui.Table;
import tn.kaz.ospas.data.FuncRequirementJPAContainer;

/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirementTable extends Table {
    public FuncRequirementTable(FuncRequirementJPAContainer datasource) {
        setContainerDataSource(datasource);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id",
                "number",
                "department",
                "acceptor",
                "date",
                "executors",
                "notice",
                "limitation",
                "docs"});
        setColumnHeaders(new String[]{"#",
                "Номер",
                "Отдел",
                "Утверждающий",
                "Дата",
                "Исполнители",
                "Извещения",
                "сроки",
                "документы"});
    }
}
