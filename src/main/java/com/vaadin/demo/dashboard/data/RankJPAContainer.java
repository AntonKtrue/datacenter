package com.vaadin.demo.dashboard.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.demo.dashboard.model.transneft.TransneftRank;

import javax.persistence.EntityManager;

/**
 * Created by user on 11.01.17.
 */
public class RankJPAContainer extends JPAContainer<TransneftRank> {
    private static final long serialVersionUID = 5832996438848438038L;

    private static final String PERSISTENCE_UNIT = "appVaadinUnit";

    public RankJPAContainer() {
        super(TransneftRank.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<TransneftRank>(TransneftRank.class, em));
    }
}
