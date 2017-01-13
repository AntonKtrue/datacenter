package com.vaadin.demo.dashboard.view.directories.staff;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.demo.dashboard.data.HierarchicalStructureContainer;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;

/**
 * Created by Anton on 13.01.2017.
 */
public class ExampleTree extends Tree {
   // HierarchicalContainer depsContainer = new HierarchicalContainer();

    public ExampleTree() {

//        Item item = null;
//        depsContainer.addContainerProperty("name", String.class, "");
//        depsContainer.addContainerProperty("id", String.class, "");
//
//        item = depsContainer.addItem("node1");
//        item.getItemProperty("name").setValue("Node1");
//        item.getItemProperty("id").setValue("1");
//        depsContainer.setChildrenAllowed("node1", true);
//
//
//        item = depsContainer.addItem("child1");
//        item.getItemProperty("name").setValue("Child1");
//        item.getItemProperty("id").setValue("2");
//        depsContainer.setChildrenAllowed("child1",false);
//        depsContainer.setParent("child1","node1");
//
//        item = depsContainer.addItem("child2");
//        item.getItemProperty("name").setValue("Child2");
//        item.getItemProperty("id").setValue("3");
//        depsContainer.setChildrenAllowed("child2",false);
//        depsContainer.setParent("child2","node1");
//
//        item = depsContainer.addItem("node2");
//        item.getItemProperty("name").setValue("Node2");
//        item.getItemProperty("id").setValue("4");
//        depsContainer.setChildrenAllowed("node2", true);
//
//
//        item = depsContainer.addItem("child3");
//        item.getItemProperty("name").setValue("Child3");
//        item.getItemProperty("id").setValue("5");
//        depsContainer.setChildrenAllowed("child3",false);
//        depsContainer.setParent("child3","node2");
//
//        item = depsContainer.addItem("child4");
//        item.getItemProperty("name").setValue("Child4");
//        item.getItemProperty("id").setValue("5");
//        depsContainer.setChildrenAllowed("child4",false);
//        depsContainer.setParent("child4","node2");
//
//        setContainerDataSource(depsContainer);
//        addValueChangeListener(new ValueChangeListener() {
//            @Override
//            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
//                Notification.show("PPPP");
//            }
//        });
//        setItemCaptionPropertyId("name");
//        setItemCaptionMode(ItemCaptionMode.PROPERTY);

        JPAContainer<TransneftStructure> structures = new HierarchicalStructureContainer();
        setContainerDataSource(structures);
        setItemCaptionPropertyId("name");
        setImmediate(true);
        setSelectable(true);

    }
}
