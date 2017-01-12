package com.vaadin.demo.dashboard.view.directories.staff.department;


import com.vaadin.addon.jpacontainer.HierarchicalEntityContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.Container;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.demo.dashboard.model.staff.Department;
import com.vaadin.ui.Tree;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Anton on 12.01.2017.
 */
public class DepartmentTree extends Tree {

    public DepartmentTree(HierarchicalEntityContainer<Department> dataSource, String itemCaptionProperty) {

        Collection c = dataSource.getItemIds();
        for(Object o : c) {
            System.out.println(((JPAContainerItem<Department>)(dataSource.getItem(o))).getEntity().getName());
        }
        setItemCaptionPropertyId(itemCaptionProperty);
        setImmediate(true);
        setSelectable(true);

        setContainerDataSource(dataSource);
        setWidth("100%");
        System.out.println(dataSource.toString());
    }
}
