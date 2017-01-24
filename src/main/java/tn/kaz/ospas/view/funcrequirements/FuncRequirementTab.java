package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;

import com.vaadin.ui.themes.ValoTheme;
import tn.kaz.ospas.data.HierarchicalJPAContainer;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.event.DashboardEventBus;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.transneft.StructureType;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.directories.staff.structure.StructureTree;


/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirementTab extends VerticalLayout {
    private Table table;
    private Button addButton, refreshButton;
    private HierarchicalJPAContainer<TransneftStructure> structureDs = new HierarchicalJPAContainer<TransneftStructure>(TransneftStructure.class, Config.PARENT_FIELD);
    private SimpleJPAContainer<FuncRequirement> funcRequiremntDs = new SimpleJPAContainer<FuncRequirement>(FuncRequirement.class);

    private TransneftStructure selectedObject;

    public FuncRequirementTab() {

        setCaption("Функциональные требования");
        buildMainScreen();


    }

    private void buildMainScreen() {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        table = new FuncRequirementTable(funcRequiremntDs);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                FuncRequirementWindow window = new FuncRequirementWindow(funcRequiremntDs);
                window.edit(Integer.valueOf(event.getItemId().toString()));

            }
        });

        Tree structureTree = new StructureTree(structureDs);
        structureTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedObject = structureDs.getItem(event.getItemId()).getEntity();
                checkAddButton();
                updateDepartmentFilters();
            }
        });
        VerticalLayout tableArea = new VerticalLayout(funcRequirementButton(funcRequiremntDs), table);
        splitPanel.setFirstComponent(structureTree);
        splitPanel.setSecondComponent(tableArea);
        splitPanel.setSplitPosition(380, Unit.PIXELS);
        addComponent(splitPanel);
        addErrorHandle(tableArea);
    }

    private void checkAddButton() {
        if(selectedObject.getType() == StructureType.OBJ) {
            addButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
        }
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

    private HorizontalLayout funcRequirementButton(final SimpleJPAContainer<FuncRequirement> datasource) {
        addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                addFuncRequirement();
            }
        });
        addButton.setEnabled(false);

        refreshButton = new Button("Обновить");
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

    private void addFuncRequirement() {
//        FuncRequirementView newFuncRequirementView = new FuncRequirementView(selectedObject);
//        addTab(newFuncRequirementView).setClosable(true);
//        newFuncRequirementView.setCaption("Новое ФТ");
//        setSelectedTab(getComponentCount() - 1);

    }

    private void updateDepartmentFilters() {
        funcRequiremntDs.setApplyFiltersImmediately(false);
        funcRequiremntDs.removeAllContainerFilters();
        if(selectedObject != null) {
            if(selectedObject.getType() == StructureType.OBJ) {
                funcRequiremntDs.addContainerFilter(new Compare.Equal("structure",selectedObject));
            } else if (selectedObject.getType() == StructureType.NPS) {
                funcRequiremntDs.addContainerFilter(new Compare.Equal("structure.parent",selectedObject));
            } else if (selectedObject.getType() == StructureType.UMN) {
                funcRequiremntDs.addContainerFilter(new Compare.Equal("structure.parent.parent",selectedObject));
            } else if (selectedObject.getType() == StructureType.OST) {
                funcRequiremntDs.addContainerFilter(new Compare.Equal("structure.parent.parent.parent",selectedObject));
            }

        }
        funcRequiremntDs.applyFilters();
    }

//    @Override
//    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
//
//    }
}
