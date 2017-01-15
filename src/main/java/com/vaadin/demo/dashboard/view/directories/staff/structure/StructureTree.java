package com.vaadin.demo.dashboard.view.directories.staff.structure;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.demo.dashboard.data.HierarchicalStructureContainer;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;
import com.vaadin.ui.Tree;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTree extends Tree {
    public StructureTree(HierarchicalStructureContainer structures) {

        setContainerDataSource(structures);
        setItemCaptionPropertyId("name");
        setImmediate(true);
        setSelectable(true);

    }
}
