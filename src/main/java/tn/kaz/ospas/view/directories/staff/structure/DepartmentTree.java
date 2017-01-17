package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.ui.Tree;
import tn.kaz.ospas.data.HierarchicalDepartmentContainer;


/**
 * Created by Anton on 16.01.2017.
 */
public class DepartmentTree extends Tree {
    public DepartmentTree(HierarchicalDepartmentContainer datasource) {
        setContainerDataSource(datasource);
        setItemCaptionPropertyId("shortName");
        setSizeUndefined();
        setImmediate(true);
        setSelectable(true);
    }
}
