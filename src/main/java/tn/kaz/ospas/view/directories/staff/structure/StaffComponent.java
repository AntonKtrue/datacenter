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
public class StaffComponent extends VerticalLayout {

    private final HierarchicalJPAContainer<TransneftStructure> structureDs  = new HierarchicalJPAContainer<TransneftStructure>(TransneftStructure.class, Config.PARENT_FIELD);
    private final HierarchicalJPAContainer<TransneftDepartment> departmentDs = new HierarchicalJPAContainer<TransneftDepartment>(TransneftDepartment.class, Config.PARENT_FIELD);;
    private final SimpleJPAContainer<TransneftEmployee> employeeDs  = new SimpleJPAContainer<TransneftEmployee>(TransneftEmployee.class);;
    private final SimpleJPAContainer<TransneftRank> rankDs = new SimpleJPAContainer<TransneftRank>(TransneftRank.class);
    private Tree structureTree = new StructureTree(structureDs);
    private Tree departmentTree = new DepartmentTree(departmentDs);
    private Table employeeTable = new EmployeeTable(employeeDs);
    private TransneftStructure selectedStructure;
    private TransneftDepartment selectedDepartment;
    private VerticalLayout structureVertical;
    private VerticalLayout departmentVertical;
    private VerticalLayout employeeVertical;

    public HierarchicalJPAContainer<TransneftStructure> getStructureDs() {
        return structureDs;
    }

    public HierarchicalJPAContainer<TransneftDepartment> getDepartmentDs() {
        return departmentDs;
    }

    public SimpleJPAContainer<TransneftEmployee> getEmployeeDs() {
        return employeeDs;
    }

    public SimpleJPAContainer<TransneftRank> getRankDs() {
        return rankDs;
    }

    public Table getEmployeeTable() {
        return employeeTable;
    }

    public Tree getStructureTree() {
        return structureTree;
    }

    public Tree getDepartmentTree() {
        return departmentTree;
    }

    {
        //employeeTable.setHeight(500f,Unit.PIXELS);
        structureTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedStructure = structureDs.getItem(event.getItemId()).getEntity();
                selectedDepartment = null;
                updateDepartmentFilters();
                updatePersonalFilters();
            }
        });
        departmentTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedDepartment = departmentDs.getItem(event.getItemId()).getEntity();
                updatePersonalFilters();
            }
        });
        updateDepartmentFilters();
        updatePersonalFilters();
    }
    public StaffComponent(boolean editable) {
        this();
       // structureVertical.addComponent(structureBarButtons(structureDs),0);
      //  departmentVertical.addComponent(departmentBarButtons(departmentDs), 0);
      //  employeeVertical.addComponent(employeeBarButtons(employeeDs),0);
        structureTree.addItemClickListener(
                new ItemClickEvent.ItemClickListener() {
                    @Override
                    public void itemClick(ItemClickEvent event) {
                        if(event.isDoubleClick()) {
                            StructureWindow window = new StructureWindow(structureDs);
                            window.edit(Integer.valueOf(event.getItemId().toString()));
                        }
                    }
                }
        );
        departmentTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if(event.isDoubleClick()) {
                    DepartmentWindow window = new DepartmentWindow(departmentDs);
                    window.edit(Integer.valueOf(event.getItemId().toString()));
                }

            }
        });
        employeeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if(event.isDoubleClick()) {
                    EmployeeWindow window = new EmployeeWindow(employeeDs, rankDs);
                    window.edit(Integer.valueOf(event.getItemId().toString()));
                }
            }
        });
    }

    public StaffComponent() {
//        VerticalLayout content = new VerticalLayout();
//        content.setSizeFull();
//        addComponent(content);
//        structureVertical = new VerticalLayout( structureTree);
//        departmentVertical = new VerticalLayout( departmentTree);
//        HorizontalLayout treeWrapper = new HorizontalLayout(structureVertical, departmentVertical);
//        treeWrapper.setSizeFull();
//        employeeVertical = new VerticalLayout( employeeTable);
//        employeeVertical.setSizeFull();
//        HorizontalLayout horizontalSplitPanel = new HorizontalLayout(treeWrapper, employeeVertical);
//       // horizontalSplitPanel.setExpandRatio(employeeVertical, 1.0f);
//       // horizontalSplitPanel.setExpandRatio(treeWrapper, 1.0f);
//        content.addComponent(horizontalSplitPanel);
        VerticalLayout content = new VerticalLayout();
        addComponent(content);
        HorizontalLayout treesWrapper = new HorizontalLayout(structureTree, departmentTree);
        content.addComponents(treesWrapper, employeeTable);
       // content.addComponent(departmentTree);
        //content.addComponent();
        //TreeTable ttable = new TreeTable();
       // ttable.setContainerDataSource(departmentDs);

       // content.addComponent(ttable);
        addErrorHandle(content);
    }

    private HorizontalLayout employeeBarButtons(final SimpleJPAContainer<TransneftEmployee> datasource) {
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
        HorizontalLayout barButton = new HorizontalLayout(addButton, refreshButton);
        barButton.setHeight("50");
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
        HorizontalLayout barButton = new HorizontalLayout(addButton, refreshButton);
        barButton.setHeight("50");
        return barButton;
    }
    private void addErrorHandle(final Component content) {
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
       // if(selectedStructure != null) {
            departmentDs.addContainerFilter(new Compare.Equal("structure",selectedStructure));
       // } else {
      //      departmentDs.addContainerFilter(new Compare.Equal("structure", null));
       // }
        departmentDs.applyFilters();
    }
    private void updatePersonalFilters() {
        employeeDs.setApplyFiltersImmediately(false);
        employeeDs.removeAllContainerFilters();
       // if(selectedDepartment != null) {
            employeeDs.addContainerFilter(new Compare.Equal("department", selectedDepartment));
       // } else {
       //     employeeDs.addContainerFilter(new Compare.Equal("department",null));
       // }
        employeeDs.applyFilters();
    }
}
