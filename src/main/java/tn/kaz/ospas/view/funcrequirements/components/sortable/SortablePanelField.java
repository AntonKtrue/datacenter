package tn.kaz.ospas.view.funcrequirements.components.sortable;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import tn.kaz.ospas.model.funcrequirement.Sequenceable;

/**
 * Created by Anton on 24.01.2017.
 */
public class SortablePanelField<T extends Sequenceable> extends CustomField<T> {

    public SortablePanelField() {

    }

    @Override
    protected Component initContent() {
        return null;
    }

    @Override
    public Class<? extends T> getType() {
        return null;
    }
}
