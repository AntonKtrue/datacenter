package tn.kaz.ospas.view.funcrequirements.components.sortable;

import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.*;

/**
 * Created by Anton on 24.01.2017.
 */
public class SortableLayout extends CustomComponent {
    private final AbstractOrderedLayout layout;
    private final DropHandler dropHandler;

    public SortableLayout() {
        layout = new VerticalLayout();
        dropHandler = new ReorderLayoutDropHandler(layout);
        final DragAndDropWrapper pane = new DragAndDropWrapper(layout);
        setCompositionRoot(pane);
    }

    public void addComponent(final Component component) {
        final WrappedComponent wrapper = new WrappedComponent(component, dropHandler);
        wrapper.setSizeUndefined();
        component.setHeight("100%");
        wrapper.setHeight("100%");
        layout.addComponent(wrapper);
    }
}
