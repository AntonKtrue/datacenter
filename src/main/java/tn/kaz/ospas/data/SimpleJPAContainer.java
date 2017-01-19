package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;


import javax.persistence.EntityManager;

/**
 * Created by user on 19.01.17.
 */
public class SimpleJPAContainer<T> extends JPAContainer<T>{
    public SimpleJPAContainer(Class<T> clazz) {
        super(clazz);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(new CachingMutableLocalEntityProvider<T>(clazz, em));
    }
}
