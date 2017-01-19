package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.transneft.TransneftRank;

import javax.persistence.EntityManager;

/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirementJPAContainer extends JPAContainer<FuncRequirement> {
    public FuncRequirementJPAContainer() {
        super(FuncRequirement.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<FuncRequirement>(FuncRequirement.class, em));
    }
}
