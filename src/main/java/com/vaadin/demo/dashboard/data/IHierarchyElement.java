package com.vaadin.demo.dashboard.data;

/**
 * Created by Anton on 12.01.2017.
 */
public interface IHierarchyElement {
    String getNodeId();
    IHierarchyElement getParent();
    Short getLevel();
    void setLevel(Short level);
    IHierarchyElement getTop();
    void setTop(IHierarchyElement top);
    String getTreePath(String theTreePath);

}
