package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;

import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.funcrequirement.Sequenceable;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Anton on 25.01.2017.
 */
public class SequenceTextContainer<T extends Sequenceable> extends VerticalLayout {

    private SimpleJPAContainer datasource;
    private RichTextArea richTextArea;
    private Table table;


    public SequenceTextContainer(final Class<? extends Sequenceable> clazz, final SimpleJPAContainer datasource, String caption, final float pixelsWidth, final FuncRequirement funcRequirement) {
        this.datasource = datasource;
        richTextArea = new RichTextArea(caption);
        richTextArea.setWidth(pixelsWidth, Unit.PIXELS);
        Button addButton = new Button("Добавить");
        Button delButton = new Button("Удалить");
        HorizontalLayout buttons = new HorizontalLayout(addButton, delButton);
        addComponents(buttons, richTextArea);
        buildTable(pixelsWidth);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    Sequenceable object = clazz.newInstance();
                    object.setSequence(datasource.size() + 1);
                    object.setDescription(richTextArea.getValue());
                    object.setFuncRequirement(funcRequirement);
                    datasource.addEntity(object);
                    datasource.refresh();
                    richTextArea.setValue("");
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        delButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(table.getValue() == null) return;
                datasource.removeItem(table.getValue());
                Collection<Object> collection = datasource.getItemIds();
                Iterator<Object> it = collection.iterator();
                int i = 1;
                while(it.hasNext()) {
                    Object itemId = it.next();
                    datasource.getItem(itemId).getItemProperty("sequence").setValue(i);
                    i++;
                }
                datasource.commit();
                table.commit();
                datasource.refresh();
            }
        });

    }

    private void buildTable(float width) {
        table = new Table();
        table.setWidth(width, Unit.PIXELS);
        table.setContainerDataSource(datasource);
        table.setVisibleColumns(new Object[]{"sequence"});
        table.addGeneratedColumn("", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                Label label = new Label(source.getItem(itemId).getItemProperty("description"), ContentMode.HTML);
                label.setHeightUndefined();
                label.setWidth("100%");
                return label;
            }
        });
        table.setDragMode(Table.TableDragMode.ROW);
        table.setSelectable(true);
        table.setMultiSelect(false);
        table.setDropHandler(new DropHandler() {
            @Override
            public void drop(DragAndDropEvent event) {
                DataBoundTransferable t = (DataBoundTransferable) event.getTransferable();
                Object sourceItemId = t.getData("itemId");
                AbstractSelect.AbstractSelectTargetDetails dropData = (AbstractSelect.AbstractSelectTargetDetails)event.getTargetDetails();
                Object targetItemId = dropData.getItemIdOver();
                if(sourceItemId.equals(targetItemId)) return;
                switch(dropData.getDropLocation()) {
                    case BOTTOM:
                        if(Integer.valueOf(targetItemId.toString()) == Integer.valueOf(sourceItemId.toString()) - 1) return;
                        move(datasource, targetItemId, sourceItemId, false);
                        datasource.commit();
                        table.commit();
                        datasource.refresh();
                        break;
                    case MIDDLE:
                    case TOP:
                        move(datasource, targetItemId, sourceItemId, true);
                        datasource.commit();
                        table.commit();
                        datasource.refresh();
                        break;
                }
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }
        });
        table.setSortContainerPropertyId("sequence");
        table.setSortEnabled(false);
        table.setPageLength(5);
        addComponent(table);

    }

    private void move(JPAContainer datasource, Object target, Object source, boolean before) {
        Collection<Object> collection = datasource.getItemIds();
        Iterator<Object> it = collection.iterator();
        int i = 1;
        Object itemId = null;
        boolean flag = false;
        while (it.hasNext()) {
            itemId = it.next();
            if(itemId.equals(source)) {
                continue;
            } else if (itemId.equals(target)) {
                Object from;
                Object to;
                if(before) {
                    from = source;
                    to = target;
                } else {
                    from = target;
                    to = source;
                }
                datasource.getItem(from).getItemProperty("sequence").setValue(i);
                i++;
                datasource.getItem(to).getItemProperty("sequence").setValue(i);
            } else {
                datasource.getItem(itemId).getItemProperty("sequence").setValue(i);
            }
            i++;
        }
    }
}
