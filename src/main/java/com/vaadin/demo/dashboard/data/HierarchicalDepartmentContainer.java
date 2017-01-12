package com.vaadin.demo.dashboard.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.demo.dashboard.model.staff.Department;

/**
 * Created by Anton on 12.01.2017.
 */
public class HierarchicalDepartmentContainer extends JPAContainer<Department> {
    private static final String PERSISTENCE_UNIT = "appVaadinUnit";
    public HierarchicalDepartmentContainer() {
       super(Department.class);

       setEntityProvider(
               new CachingLocalEntityProvider<Department>(
                       Department.class,
                       JPAContainerFactory
                        .createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT)
               )
       );

       setParentProperty("parent");


    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId)
                && getItem(itemId).getEntity().isSuperDepartment();
    }
}
