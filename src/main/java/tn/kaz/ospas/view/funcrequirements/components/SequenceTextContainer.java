package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.event.DataBoundTransferable;


import com.vaadin.event.ItemClickEvent;
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
   // private RichTextArea richTextArea;
    private Table table;
    private String caption;
    private final Class<? extends Sequenceable> clazz;
    private FuncRequirement funcRequirement;


    public SequenceTextContainer(final Class<? extends Sequenceable> clazz, final SimpleJPAContainer datasource, String caption, final float pixelsWidth, final FuncRequirement funcRequirement) {
        this.datasource = datasource;
        this.caption = caption;
        this.clazz = clazz;
        this.funcRequirement = funcRequirement;
        Button addButton = new Button("Добавить");
        Button delButton = new Button("Удалить");
        HorizontalLayout buttons = new HorizontalLayout(addButton, delButton);
        addComponents(buttons);
        setCaption(caption);
        buildTable(pixelsWidth);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    Sequenceable editItem = clazz.newInstance();
                    editItem.setSequence(datasource.size() + 1);
                    editItem.setFuncRequirement(funcRequirement);
                    RichTextWindow window = new RichTextWindow(editItem);
                    UI.getCurrent().addWindow(window);
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
                if (t.getSourceComponent() != table) {
                    return;
                }
                Object sourceItemId = t.getData("itemId");

                AbstractSelect.AbstractSelectTargetDetails dropData = (AbstractSelect.AbstractSelectTargetDetails)event.getTargetDetails();
                Object targetItemId = dropData.getItemIdOver();
                if(sourceItemId.equals(targetItemId)) return;
                if(dropData == null && dropData.getDropLocation() == null) return;
                switch(dropData.getDropLocation()) {
                    case BOTTOM:
                        if(Integer.valueOf(targetItemId.toString()) == Integer.valueOf(sourceItemId.toString()) - 1) return;
                        move(datasource, targetItemId, sourceItemId, false);
                        datasource.commit();
                        //table.commit();
                        datasource.refresh();
                        break;
                    case MIDDLE:
                    case TOP:
                        move(datasource, targetItemId, sourceItemId, true);
                        datasource.commit();
                        //table.commit();
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
        table.setPageLength(7);


        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Sequenceable editItem = (Sequenceable)datasource.getItem(event.getItemId()).getEntity();
                    RichTextWindow window = new RichTextWindow(editItem);
                    UI.getCurrent().addWindow(window);
                }
            }
        });
        table.setSizeFull();
        addComponent(table);
        setExpandRatio(table,1f);

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

    private class RichTextWindow extends Window {
        private FormLayout layout = new FormLayout();
        private HorizontalLayout wrapper = new HorizontalLayout();
        private Sequenceable editItem;
        private RichTextArea richTextArea;

        public RichTextWindow(Sequenceable editItem) {
            this.editItem = editItem;
            setCaption(caption);

            setWidth(800f, Unit.PIXELS);
            setHeight(350f, Unit.PIXELS);
            setModal(true);
            layout.setSizeFull();
            setContent(layout);
            RichTextArea rta = buildRichTextArea();
            HorizontalLayout buttons = buildButtons();
            layout.addComponents(rta, buttons);


        }

        private HorizontalLayout buildButtons() {
            Button saveButton = new Button("Сохранить");
            Button discardButton = new Button("Отменить");
            saveButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                        editItem.setDescription(richTextArea.getValue());
                        datasource.addEntity(editItem);
                        //datasource.refresh();
                        richTextArea.setValue("");
                        RichTextWindow.this.close();

                }
            });
            discardButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    RichTextWindow.this.close();
                }
            });
            HorizontalLayout buttons = new HorizontalLayout(saveButton, discardButton);
            return buttons;
        }

        private RichTextArea buildRichTextArea() {
            richTextArea = new RichTextArea();
            richTextArea.setWidth(750f, Unit.PIXELS);
            richTextArea.setNullRepresentation("");
            richTextArea.setValue(editItem.getDescription());
            return richTextArea;
        }
    }


}
