package tn.kaz.ospas.data;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftStructure;

import javax.persistence.EntityManager;

/**
 * Created by Anton on 16.01.2017.
 */
public class HierarchicalDepartmentContainer extends JPAContainer<TransneftDepartment> {
    public HierarchicalDepartmentContainer() {
        super(TransneftDepartment.class);
        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(Config.JPA_UNIT);
        setEntityProvider(
                new CachingMutableLocalEntityProvider<TransneftDepartment>(
                        TransneftDepartment.class,
                        em));
        setParentProperty("parent");

    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId)
                && getItem(itemId).getEntity().getChilds().size() > 0   ;
    }

}
