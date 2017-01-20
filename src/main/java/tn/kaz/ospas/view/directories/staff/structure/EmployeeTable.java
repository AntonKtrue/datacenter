package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.ui.Table;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.transneft.TransneftEmployee;

/**
 * Created by Anton on 16.01.2017.
 */
public class EmployeeTable extends Table {
    public EmployeeTable(SimpleJPAContainer<TransneftEmployee> datasource) {
        datasource.addNestedContainerProperty("rank.name");
        setContainerDataSource(datasource);
        setSelectable(true);
        setWidth("100%");
        configColumns();
    }

    private void configColumns() {
        setVisibleColumns(new Object[]{"id", "firstName", "lastName", "patroName","rank.name"});
        setColumnHeaders(new String[]{"#","Имя", "Фамилия","Отчество","Должность"});
    }
}
