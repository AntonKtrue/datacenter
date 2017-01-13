package com.vaadin.demo.dashboard.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.demo.dashboard.model.Config;
import com.vaadin.demo.dashboard.model.transneft.TransneftRank;

import javax.persistence.EntityManager;

/**
 * Created by user on 11.01.17.
 */
@SuppressWarnings("serial")
public class RankJPAContainer extends JPAContainer<TransneftRank> {

    public RankJPAContainer() {
        super(TransneftRank.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<TransneftRank>(TransneftRank.class, em));
    }
}
