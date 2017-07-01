package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.ui.Tree;
import com.vaadin.ui.Window;
import tn.kaz.ospas.data.HierarchicalJPAContainer;
import tn.kaz.ospas.model.HasParent;


/**
 * Created by Anton on 31.01.2017.
 * TODO: extract superclass from tree classes
 */
public class HierarchicalContainerTree<T  extends HasParent> extends Tree {
    public HierarchicalContainerTree(final HierarchicalJPAContainer<T> datasource) {
        setContainerDataSource(datasource);


    }


}
