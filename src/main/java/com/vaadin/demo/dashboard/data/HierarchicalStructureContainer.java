package com.vaadin.demo.dashboard.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.jndijta.CachingMutableEntityProvider;
import com.vaadin.demo.dashboard.model.Config;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;

import javax.persistence.EntityManager;

/**
 * Created by Anton on 13.01.2017.
 */
public class HierarchicalStructureContainer extends JPAContainer<TransneftStructure> {
    public HierarchicalStructureContainer() {
        super(TransneftStructure.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(
                new CachingMutableLocalEntityProvider<TransneftStructure>(
                        TransneftStructure.class,
                        em));
        setParentProperty("parent");

    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId)
             && getItem(itemId).getEntity().getChilds().size() > 0   ;
    }
}
