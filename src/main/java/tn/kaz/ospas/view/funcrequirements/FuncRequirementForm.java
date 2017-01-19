package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.ui.*;
import org.vaadin.viritin.fields.MultiSelectTable;
import org.vaadin.viritin.form.AbstractForm;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

/**
 * Created by user on 19.01.17.
 */
public class FuncRequirementForm extends AbstractForm<FuncRequirement> {

    TextField number = new TextField("номер");
    DateField date = new PopupDateField("Дата создания ФТ");
    MultiSelectTable groups = new MultiSelectTable("Goups")
            .withProperties("name").withColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);


    @Override
    protected Component createContent() {
        return null;
    }
}
