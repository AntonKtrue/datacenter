package com.vaadin.demo.dashboard.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.demo.dashboard.model.Config;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;

/**
 * Created by Anton on 13.01.2017.
 */
public class HierarchicalStructureContainer extends JPAContainer<TransneftStructure> {
    public HierarchicalStructureContainer() {
        super(TransneftStructure.class);
        setEntityProvider(
                new CachingLocalEntityProvider<TransneftStructure>(
                        TransneftStructure.class,
                        JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT)
                )
        );
        setParentProperty("parent");

    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId)
             && getItem(itemId).getEntity().getChilds().size() > 0   ;
    }
}
