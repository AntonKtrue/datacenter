package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import tn.kaz.ospas.model.funcrequirement.Sequenceable;

/**
 * Created by user on 24.01.17.
 */
public class TextBlockField<T extends Sequenceable> extends CustomField<T> {

    private FieldGroup fieldGroup;
    private Table table;


    private Class<T> clazz;
    public TextBlockField(Class<T> clazz) {
        this.clazz = clazz;
        ListSelect listSelect = new ListSelect();

    }

    @Override
    protected Component initContent() {
        return null;
    }

    @Override
    public Class<? extends T> getType() {
        return clazz;
    }
}
