package tn.kaz.ospas.view.funcrequirements.components.sortable;

import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.DropTarget;
import com.vaadin.event.dd.TargetDetails;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.event.dd.acceptcriteria.SourceIsTarget;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;

import java.util.Iterator;

/**
 * Created by Anton on 24.01.2017.
 */
public class ReorderLayoutDropHandler implements DropHandler {
    public final AbstractOrderedLayout layout;

    public ReorderLayoutDropHandler(final AbstractOrderedLayout layout) {
        this.layout = layout;
    }


    @Override
    public void drop(final DragAndDropEvent dropEvent) {
        final Transferable transferable = dropEvent.getTransferable();
        final Component sourceComponent = transferable.getSourceComponent();
        if (sourceComponent instanceof WrappedComponent) {
            final TargetDetails dropTargetData = dropEvent.getTargetDetails();
            final DropTarget target = dropTargetData.getTarget();

            boolean sourceWasAfterTarget = true;
            int index = 0;
            final Iterator<Component> componentIterator = layout.iterator();
            Component next = null;
            while (next != target && componentIterator.hasNext()) {
                next = componentIterator.next();
                if (next != sourceComponent) {
                    index++;
                } else {
                    sourceWasAfterTarget = false;
                }
            }

            if (next == null || next != target) {
                //component not found - if dragging from another layout
                return;
            }

            //drop on top of target ?
            if (dropTargetData.getData("verticalLocation").equals(
                    VerticalDropLocation.TOP.toString()
            )) {
                index--;
                if(index < 0) {
                    index = 0;
                }
            }
            layout.removeComponent(sourceComponent);
            layout.addComponent(sourceComponent,index);
        }
    }

    @Override
    public AcceptCriterion getAcceptCriterion() {
        return new Not(SourceIsTarget.get());
    }


}
