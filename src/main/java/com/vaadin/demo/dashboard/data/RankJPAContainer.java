package com.vaadin.demo.dashboard.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.demo.dashboard.model.staff.Rank;

import javax.persistence.EntityManager;

/**
 * Created by user on 11.01.17.
 */
public class RankJPAContainer extends JPAContainer<Rank> {
    private static final long serialVersionUID = 5832996438848438038L;

    /**
     * Nome da unidade persistencia. De acordo com o arquivo <code>persistence.xml</code>.
     */
    private static final String PERSISTENCE_UNIT = "appVaadinUnit";

    public RankJPAContainer() {
        super(Rank.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<Rank>(Rank.class, em));
    }
}
