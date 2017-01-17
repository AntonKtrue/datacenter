package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.data.util.filter.Compare;
import tn.kaz.ospas.data.EmployeeJPAContainer;
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
    private Table personalTable;

    private TransneftStructure selectedStructure;
    private TransneftDepartment selectedDepartment;

    private final HierarchicalStructureContainer structureDs;
    private final HierarchicalDepartmentContainer departmentDs;
    private final EmployeeJPAContainer employeeDs;

    public StructureTab() {
        structureDs = new HierarchicalStructureContainer();
        structureTree = new StructureTree(structureDs);
        structureTree.addItemClickListener(
                new ItemClickEvent.ItemClickListener() {
                    @Override
                    public void itemClick(ItemClickEvent event) {
                        selectedStructure = structureDs.getItem(event.getItemId()).getEntity();
                        updateDepartmentFilters();
                    }
                }
        );
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);

        HorizontalSplitPanel wrapper = new HorizontalSplitPanel();

        content.addComponent(wrapper);
        HorizontalLayout treesWrapper = new HorizontalLayout();
        VerticalLayout structureVertical = new VerticalLayout( structureBarButtons(structureDs),structureTree);
        treesWrapper.addComponent(structureVertical);

        departmentDs = new HierarchicalDepartmentContainer();
        departmentTree = new DepartmentTree(departmentDs);
        departmentTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedDepartment = departmentDs.getItem(event.getItemId()).getEntity();
                updatePersonalFilters();
            }
        });
        VerticalLayout departmentVertical = new VerticalLayout(departmentBarButtons(departmentDs), departmentTree );
        treesWrapper.addComponent(departmentVertical);

        employeeDs = new EmployeeJPAContainer();
        EmployeeTable employeeTable = new EmployeeTable(employeeDs);
        VerticalLayout employeeVertical = new VerticalLayout(employeeBarButtons(employeeDs), employeeTable);
        wrapper.setFirstComponent(treesWrapper);
        wrapper.setSecondComponent(employeeVertical);
        wrapper.setSplitPosition(450, Unit.PIXELS);
        addErrorHandle(content);
    }

    private HorizontalLayout employeeBarButtons(final  EmployeeJPAContainer datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EmployeeWindow window = new EmployeeWindow(datasource);
                window.create(selectedDepartment);
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



    private void updateDepartmentFilters() {
        departmentDs.setApplyFiltersImmediately(false);
        departmentDs.removeAllContainerFilters();
        if(selectedStructure != null) {
            departmentDs.addContainerFilter(new Compare.Equal("structure",selectedStructure));
        }
        departmentDs.applyFilters();
    }

    private void updatePersonalFilters() {
    }
}
