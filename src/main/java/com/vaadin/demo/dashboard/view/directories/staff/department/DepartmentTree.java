package com.vaadin.demo.dashboard.view.directories.staff.department;

import com.vaadin.data.Container;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Tree;

/**
 * Created by Anton on 12.01.2017.
 */
public class DepartmentTree extends Tree {

    public DepartmentTree(Container dataSource) {
        setContainerDataSource(dataSource);
        setWidth("100%");
    }
}
