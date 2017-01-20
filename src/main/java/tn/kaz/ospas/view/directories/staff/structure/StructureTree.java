package tn.kaz.ospas.view.directories.staff.structure;

import tn.kaz.ospas.data.HierarchicalJPAContainer;

import com.vaadin.ui.Tree;
import tn.kaz.ospas.model.transneft.TransneftStructure;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTree extends Tree {
    public StructureTree(HierarchicalJPAContainer<TransneftStructure> structures) {
        setContainerDataSource(structures);
        setItemCaptionPropertyId("name");
        setSizeUndefined();
        setImmediate(true);
        setSelectable(true);
    }
}
