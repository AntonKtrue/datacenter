package tn.kaz.ospas.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Anton on 19.01.2017.
 */
public class GuiHelper {
    public static VerticalLayout makeTabContent(String caption, Alignment contentAlignment, Component contentComponent) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setCaption(caption);
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.setSpacing(true);
        wrapper.addComponent(content);
        wrapper.setComponentAlignment(content, contentAlignment);
        Label title = new Label(caption);
        title.addStyleName(ValoTheme.LABEL_H1);
        title.setSizeUndefined();
        content.addComponent(title);
        content.setComponentAlignment(title, Alignment.TOP_CENTER);
        content.addComponent(contentComponent);
        return wrapper;
    }

    public static TextField makeTextField(String caption, Class clazz, String property) {
        TextField field = new TextField(caption);
        field.setNullRepresentation("");
        field.addValidator(new BeanValidator(clazz, property));
        field.setWidth(250f, Sizeable.Unit.PIXELS);
        return field;
    }

}
