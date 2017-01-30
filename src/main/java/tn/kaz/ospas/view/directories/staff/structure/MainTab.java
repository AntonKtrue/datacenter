package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.data.util.filter.Compare;
import tn.kaz.ospas.data.*;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.transneft.*;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;

/**
 * Created by Anton on 13.01.2017.
 */
public class MainTab extends VerticalLayout {
    private Tree structureTree;
    private Tree departmentTree;
    private Table employeeTable;

    private TransneftStructure selectedStructure;
    private TransneftDepartment selectedDepartment;

    private final HierarchicalJPAContainer<TransneftStructure> structureDs;
    private final HierarchicalJPAContainer<TransneftDepartment> departmentDs;
    private final SimpleJPAContainer<TransneftEmployee> employeeDs;
    private final SimpleJPAContainer<TransneftRank> rankDs = new SimpleJPAContainer<TransneftRank>(TransneftRank.class);

    public MainTab() {
        structureDs = new HierarchicalJPAContainer<TransneftStructure>(TransneftStructure.class, Config.PARENT_FIELD);
        structureTree = new StructureTree(structureDs);
        structureTree.addItemClickListener(
                new ItemClickEvent.ItemClickListener() {
                    @Override
                    public void itemClick(ItemClickEvent event) {
                        selectedStructure = structureDs.getItem(event.getItemId()).getEntity();
                        selectedDepartment = null;
                        updateDepartmentFilters();
                        updatePersonalFilters();
                    }
                }
        );
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);
        HorizontalLayout treeWrapper = new HorizontalLayout();

        treeWrapper.setSizeFull();
        treeWrapper.setWidth("100%");



        VerticalLayout structureVertical = new VerticalLayout(structureTree, structureBarButtons(structureDs));
        treeWrapper.addComponent(structureVertical);

        departmentDs = new HierarchicalJPAContainer<TransneftDepartment>(TransneftDepartment.class, Config.PARENT_FIELD);
        departmentTree = new DepartmentTree(departmentDs);
        departmentTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedDepartment = departmentDs.getItem(event.getItemId()).getEntity();
                updatePersonalFilters();

            }
        });
        VerticalLayout departmentVertical = new VerticalLayout(departmentTree, departmentBarButtons(departmentDs));
        treeWrapper.addComponent(departmentVertical);

        employeeDs = new SimpleJPAContainer<TransneftEmployee>(TransneftEmployee.class);

        employeeTable = new EmployeeTable(employeeDs);
        employeeTable.setHeight(500f,Unit.PIXELS);
        employeeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if(event.isDoubleClick()) {
                    EmployeeWindow window = new EmployeeWindow(employeeDs, rankDs);
                    window.edit(Integer.valueOf(event.getItemId().toString()));
                }
            }
        });
        VerticalLayout personalVertical = new VerticalLayout(personalBarButtons(employeeDs), employeeTable);
        content.addComponent(personalVertical);
        content.addComponent(treeWrapper);
        addErrorHandle(content);
    }

    private HorizontalLayout personalBarButtons(final SimpleJPAContainer<TransneftEmployee> datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EmployeeWindow window = new EmployeeWindow(datasource, rankDs);
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

    private HorizontalLayout departmentBarButtons(final  HierarchicalJPAContainer<TransneftDepartment> datasource) {
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


        HorizontalLayout barButton = new HorizontalLayout(addButton, refreshButton);
        barButton.setHeight("50");
        return barButton;
    }

    private HorizontalLayout structureBarButtons(final HierarchicalJPAContainer<TransneftStructure> datasource) {
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
        employeeDs.setApplyFiltersImmediately(false);
        employeeDs.removeAllContainerFilters();
        if(selectedDepartment != null) {
            employeeDs.addContainerFilter(new Compare.Equal("department", selectedDepartment));
        }
        employeeDs.applyFilters();
    }
}
