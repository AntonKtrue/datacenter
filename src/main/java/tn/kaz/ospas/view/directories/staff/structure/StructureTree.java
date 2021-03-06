package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.event.ItemClickEvent;
import tn.kaz.ospas.data.HierarchicalJPAContainer;

import com.vaadin.ui.Tree;
import tn.kaz.ospas.model.transneft.StructureType;
import tn.kaz.ospas.model.transneft.TransneftStructure;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTree extends Tree {
    public StructureTree(final HierarchicalJPAContainer<TransneftStructure> datasource) {
        setContainerDataSource(datasource);
        setItemCaptionPropertyId("name");
        setSizeUndefined();
        setImmediate(true);
        setSelectable(true);
    }

    public static void expand(StructureTree tree, StructureType type) {
        Collection c = tree.items.getItemIds();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Object itemId = it.next();
            if(tree.getContainerDataSource().getItem(itemId).getItemProperty("type").getValue().equals(type)) {
                tree.expandItem(itemId);
            }
        }
    }


}
