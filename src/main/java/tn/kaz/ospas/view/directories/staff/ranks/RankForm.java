package tn.kaz.ospas.view.directories.staff.ranks;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by user on 22.01.17.
 */
public class RankForm extends AbstractForm {
    TextField name = new MTextField("Полное название");
    TextField shortName = new MTextField("Краткое название");


    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        name,
                        shortName
                ), getToolbar()
        );
    }
}
