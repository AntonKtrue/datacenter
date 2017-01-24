package tn.kaz.ospas.view.funcrequirements.components.sortable;

import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.DropTarget;
import com.vaadin.event.dd.TargetDetails;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.event.dd.acceptcriteria.SourceIsTarget;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

/**
 * Created by Anton on 24.01.2017.
 */
public class ReorderTableEntityDropHandler implements DropHandler  {

    public final Table table;

    public ReorderTableEntityDropHandler(Table table) {
            this.table = table;
    }

    @Override
    public void drop(DragAndDropEvent dropEvent) {
        final Transferable transferable = dropEvent.getTransferable();
        final Component sourceComponent = transferable.getSourceComponent();

    }

    @Override
    public AcceptCriterion getAcceptCriterion() {
        return new Not(SourceIsTarget.get());
    }
}
