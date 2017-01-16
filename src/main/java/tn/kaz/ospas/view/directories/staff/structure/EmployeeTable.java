package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.ui.Table;
import tn.kaz.ospas.data.EmployeeJPAContainer;

/**
 * Created by Anton on 16.01.2017.
 */
public class EmployeeTable extends Table {
    public EmployeeTable(EmployeeJPAContainer datasource) {
        setContainerDataSource(datasource);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "firstName", "lastName", "patroName","rank"});
        setColumnHeaders(new String[]{"#","Полное название", "Короткое название"});
    }
}
