package tn.kaz.ospas.view.directories.staff.structure;

import tn.kaz.ospas.data.HierarchicalDepartmentContainer;
import tn.kaz.ospas.data.HierarchicalStructureContainer;
import tn.kaz.ospas.model.transneft.StructureType;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;

/**
 * Created by Anton on 13.01.2017.
 */
public class StructureTab extends VerticalLayout {
    private Tree structureTree;
    private Tree departmentTree;

    private TransneftStructure selectedStructure;
    private TransneftDepartment selectedDepartment;

    public StructureTab() {
        final HierarchicalStructureContainer structureDs = new HierarchicalStructureContainer();
        structureTree = new StructureTree(structureDs);
        structureTree.addItemClickListener(
                new ItemClickEvent.ItemClickListener() {
                    @Override
                    public void itemClick(ItemClickEvent event) {
                        selectedStructure = structureDs.getItem(event.getItemId()).getEntity();
                    }
                }
        );
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);
        HorizontalLayout wrapper = new HorizontalLayout();
        wrapper.setSizeFull();
        wrapper.setWidth("100%");
        content.addComponent(wrapper);


        VerticalLayout structureVertical = new VerticalLayout(structureTree, structureBarButtons(structureDs));
        wrapper.addComponent(structureVertical);
        wrapper.setComponentAlignment(structureVertical, Alignment.TOP_LEFT);
        final HierarchicalDepartmentContainer departmentDs = new HierarchicalDepartmentContainer();
        DepartmentTree departmentTree = new DepartmentTree(departmentDs);

        VerticalLayout departmentVertical = new VerticalLayout(departmentTree, departmentBarButtons(departmentDs));
        wrapper.addComponent(departmentVertical);
        wrapper.setComponentAlignment(departmentVertical,Alignment.TOP_LEFT);
        addErrorHandle(content);
    }

    private HorizontalLayout departmentBarButtons(final  HierarchicalDepartmentContainer datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                DepartmentWindow window = new DepartmentWindow(datasource);
                window.create(selectedDepartment, selectedStructure);
            }
        });

        Button refreshButton = new Button("Обновить");
        refreshButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                datasource.refresh();
            }
        });

        Button[] buttons = {addButton, refreshButton};
        HorizontalLayout barButton = new HorizontalLayout();
        barButton.setHeight("50");

        for (Button b: buttons) {
            b.setStyleName(Runo.BUTTON_BIG);
            barButton.addComponent(b);
            barButton.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
        }
        return barButton;
    }

    private HorizontalLayout structureBarButtons(final HierarchicalStructureContainer datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                StructureWindow window = new StructureWindow(datasource);
                window.create(selectedStructure);
            }
        });

        Button refreshButton = new Button("Обновить");
        refreshButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                datasource.refresh();
            }
        });

        Button[] buttons = {addButton, refreshButton};
        HorizontalLayout barButton = new HorizontalLayout();
        barButton.setHeight("50");

        for (Button b: buttons) {
            b.setStyleName(Runo.BUTTON_BIG);
            barButton.addComponent(b);
            barButton.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
        }
        return barButton;
    }
    private void addErrorHandle(final VerticalLayout content) {
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "Произошла ошибка!:\n";
                for (Throwable t = event.getThrowable(); t != null;
                     t = t.getCause())
                    if (t.getCause() == null)
                        cause += t.getClass().getName() + "\n";

                Notification.show(cause, Notification.Type.ERROR_MESSAGE);
                doDefault(event);
            }
        });
    }
}
