package tn.kaz.ospas.data;

import com.vaadin.data.Container;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Anton on 01.02.2017.
 */
public class StructureTreeTableContainer  extends SQLContainer implements Container.Hierarchical {

    public StructureTreeTableContainer(QueryDelegate delegate) throws SQLException {
        super(delegate);
    }

    @Override
    public Collection<?> getChildren(Object o) {
        return null;
    }

    @Override
    public Object getParent(Object o) {
        return null;
    }

    @Override
    public Collection<?> rootItemIds() {
        return null;
    }

    @Override
    public boolean setParent(Object o, Object o1) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean areChildrenAllowed(Object o) {
        return false;
    }

    @Override
    public boolean setChildrenAllowed(Object o, boolean b) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean isRoot(Object o) {
        return false;
    }

    @Override
    public boolean hasChildren(Object o) {
        return false;
    }
}
