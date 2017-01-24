package tn.kaz.ospas.view.funcrequirements.components.sortable;

import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;

/**
 * Created by Anton on 24.01.2017.
 */
public class WrappedComponent extends DragAndDropWrapper {
    private final DropHandler dropHandler;

    public WrappedComponent(final Component component,
                            final DropHandler dropHandler) {
        super(component);
        this.dropHandler = dropHandler;
        setDragStartMode(DragStartMode.WRAPPER);
    }

    @Override
    public DropHandler getDropHandler() {
        return dropHandler;
    }
}
