package com.vaadin.demo.dashboard.view.directories.staff.structure;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.demo.dashboard.data.HierarchicalStructureContainer;
import com.vaadin.demo.dashboard.model.transneft.StructureType;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;

/**
 * Created by Anton on 13.01.2017.
 */
@SuppressWarnings("serial")
public class StructureWindow extends Window implements Button.ClickListener {

    private FormLayout layout;
    private BeanFieldGroup<TransneftStructure> binder;
    private HorizontalLayout buttons;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;

    private HierarchicalStructureContainer datasource;

    public StructureWindow(HierarchicalStructureContainer datasource) {
        this.datasource = datasource;
        itit();
        setModal(true);
    }

    protected void itit() {
        layout = new FormLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        saveButton = new Button("Сохранить");
        saveButton.addClickListener(this);
        saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        cancelButton = new Button("Отменить");
        cancelButton.addClickListener(this);
        cancelButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);

        deleteButton = new Button("Удалить");
        deleteButton.addClickListener(this);
        deleteButton.setVisible(false);

        buttons = new HorizontalLayout(saveButton, cancelButton, deleteButton);

        setContent(layout);
        setHeight("470");
        setWidth("600");

    }

    public void edit(Integer id) {
        try {
            setCaption("Редактирование структуры");
            TransneftStructure m = datasource.getItem(id).getEntity();
            bindingFields(m);
            deleteButton.setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create(StructureType type, TransneftStructure parent) {
        setCaption("Новая запись");
        bindingFields(new TransneftStructure(type, parent));
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(TransneftStructure m) {
        binder = new BeanFieldGroup<TransneftStructure>(TransneftStructure.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Полное название", "name");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Краткое название", "shortName");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Шифр", "code");
        field.setWidth("80");
        layout.addComponent(field);


        layout.addComponent(buttons);
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton() == saveButton) {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException e) {
                Notification.show("Возникла ошибка 2!");
                return;
            }

            try {
                datasource.addEntity(binder.getItemDataSource().getBean());
                datasource.refresh();
                Notification.show("Запись добавлена!", Notification.Type.HUMANIZED_MESSAGE);
            } catch(Exception e) {

                Notification.show("Возникла ошибка 4!\n"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
                return;
            }
        } else if (event.getButton() == deleteButton) {
            ConfirmDialog.show(this.getUI(), "Подтвердите удаление..",
                    new ConfirmDialog.Listener() {
                        public void onClose(ConfirmDialog dialog) {
                            if (dialog.isConfirmed()) {
                                try {
                                    datasource.removeItem(binder.getItemDataSource().getBean().getId());
                                } catch (Exception e) {
                                    Notification.show("Ошибка удаления!\n"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
                                }
                                close();
                            }
                        }
                    });
            return;
        } else if (event.getButton() == cancelButton) {
            binder.discard();
        }
        close();
    }
}
