package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.HasParent;

import javax.persistence.EntityManager;

/**
 * Created by Anton on 20.01.2017.
 */
public class HierarchicalJPAContainer<T extends HasParent> extends JPAContainer<T> {
    public HierarchicalJPAContainer(Class<T> clazz, String parentField) {
        super(clazz);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(
                new CachingMutableLocalEntityProvider<T>(
                        clazz,
                        em));
        setParentProperty(parentField);
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId)
                && getItem(itemId).getEntity().getChilds().size() > 0   ;
    }
}
