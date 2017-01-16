package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.transneft.TransneftRank;

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
