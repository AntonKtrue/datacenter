package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.executors.ExecutorType;

import javax.persistence.EntityManager;

/**
 * Created by Anton on 19.01.2017.
 */
public class ExecutorsTypeJPAContainer extends JPAContainer<ExecutorType> {
    public ExecutorsTypeJPAContainer() {
        super(ExecutorType.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<ExecutorType>(ExecutorType.class, em));
    }

}
