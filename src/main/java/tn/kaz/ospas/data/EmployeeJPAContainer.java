package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftRank;

import javax.persistence.EntityManager;

/**
 * Created by Anton on 16.01.2017.
 */
public class EmployeeJPAContainer extends JPAContainer<TransneftEmployee> {
    public EmployeeJPAContainer() {
        super(TransneftEmployee.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<TransneftEmployee>(TransneftEmployee.class, em));
    }


}
