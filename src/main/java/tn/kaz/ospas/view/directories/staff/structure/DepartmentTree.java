package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.ui.Tree;
import tn.kaz.ospas.data.HierarchicalJPAContainer;
import tn.kaz.ospas.model.transneft.TransneftDepartment;


/**
 * Created by Anton on 16.01.2017.
 */
public class DepartmentTree extends Tree {
    public DepartmentTree(HierarchicalJPAContainer<TransneftDepartment> datasource) {
        setContainerDataSource(datasource);
        setItemCaptionPropertyId("caption");
        setSizeUndefined();
        setImmediate(true);
        setSelectable(true);
    }
}
