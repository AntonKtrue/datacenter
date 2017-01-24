package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.ui.ComboBox;
import tn.kaz.ospas.data.SimpleJPAContainer;

/**
 * Created by Anton on 24.01.2017.
 */
public class ManyToOneComboBox extends ComboBox {
    public ManyToOneComboBox(String caption, SimpleJPAContainer datasource, String captionProperty, boolean required) {
        setCaption(caption);
        setContainerDataSource(datasource);
        setImmediate(true);
        setItemCaptionPropertyId(captionProperty);
        setConverter(new SingleSelectConverter(this));
        setRequired(required);


    }
}
