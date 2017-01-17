package tn.kaz.ospas.view.directories.staff.structure;

import tn.kaz.ospas.data.HierarchicalStructureContainer;
import com.vaadin.ui.Tree;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTree extends Tree {
    public StructureTree(HierarchicalStructureContainer structures) {
        setContainerDataSource(structures);
        setItemCaptionPropertyId("name");
        setSizeUndefined();
        setImmediate(true);
        setSelectable(true);
    }
}
