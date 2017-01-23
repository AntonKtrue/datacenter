package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.data.HierarchicalJPAContainer;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.event.DashboardEventBus;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.transneft.StructureType;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import com.vaadin.ui.TabSheet.CloseHandler;
import tn.kaz.ospas.view.directories.staff.structure.StructureTree;

/**
 * Created by Anton on 20.01.2017.
 */

public class FuncRequirementView extends TabSheet implements View, CloseHandler {
    private Table table;
    private Button addButton, refreshButton, addExistButton;
    private HierarchicalJPAContainer<TransneftStructure> structureDs = new HierarchicalJPAContainer<TransneftStructure>(TransneftStructure.class, Config.PARENT_FIELD);
    private SimpleJPAContainer<FuncRequirement> funcRequiremntDs = new SimpleJPAContainer<FuncRequirement>(FuncRequirement.class);

    private TransneftStructure selectedObject;

    public FuncRequirementView() {

        setSizeFull();
        addStyleName("reports");
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setCloseHandler(this);
        DashboardEventBus.register(this);

        addTab(buildMainScreen());

    }

    private Component buildMainScreen() {
        final VerticalLayout mainScreen = new VerticalLayout();
        mainScreen.setSizeFull();
        mainScreen.setCaption("Функциональные требования");
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        mainScreen.addComponent(splitPanel);
        table = new FuncRequirementTable(funcRequiremntDs);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                FuncRequirementWindow window = new FuncRequirementWindow(funcRequiremntDs);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });

        Tree structureTree = new StructureTree(structureDs);
        structureTree.expandItem(structureDs.firstItemId());


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

        addErrorHandle(mainScreen);
        return mainScreen;
    }

    private HorizontalLayout funcRequirementButton(final SimpleJPAContainer<FuncRequirement> datasource) {
        addButton = new Button("Добавить новое");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {


                ConfirmDialog.show(UI.getCurrent(), "Подтвердите создание нового ФТ",
                        "Создать новое ФТ?", "Создать", "Отмена", new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog confirmDialog) {
                                if(confirmDialog.isConfirmed()) {
                                    Notification.show("Confirmed");
                                    addFuncRequirement();
                                } else {
                                    Notification.show("Отмена действия");
                                }
                            }
                        });

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

        addExistButton = new Button("Добавить существующее");
        addExistButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });


        HorizontalLayout barButton = new HorizontalLayout(addButton, refreshButton, addExistButton);
        barButton.setHeight("50");
        return barButton;
    }

    private void addFuncRequirement() {
        FuncRequirementEditor funcRequirementEditor = new FuncRequirementEditor(selectedObject, funcRequiremntDs);
        Tab addFtTab = addTab(funcRequirementEditor);
        addFtTab.setClosable(true);

        addFtTab.setCaption("ФТ " + selectedObject.getParent().getParent().getParent().getCode() +
                "-" + selectedObject.getParent().getParent().getCode() + "-" + selectedObject.getCode());
        setSelectedTab(getComponentCount() - 1);

    }

    @Override
    public void onTabClose(TabSheet tabSheet, Component component) {
        removeComponent(component);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    private void checkAddButton() {
        if(selectedObject.getType() == StructureType.OBJ) {
            addButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
        }
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
