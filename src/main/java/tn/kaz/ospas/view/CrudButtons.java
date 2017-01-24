package tn.kaz.ospas.view;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.view.funcrequirements.FuncRequirementEditor;

/**
 * Created by Anton on 19.01.2017.
 */
public class CrudButtons<T extends Identity> extends HorizontalLayout implements Button.ClickListener {
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;

    private BeanFieldGroup<T> binder;
    private JPAContainer<T> datasource;
    private Component owner;

    public CrudButtons(JPAContainer<T> datasource, BeanFieldGroup<T> binder, Component owner) {
        this.datasource = datasource;
        this.owner = owner;
        this.binder = binder;
        saveButton = new Button("Сохранить");
        saveButton.addClickListener(this);
        //saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        cancelButton = new Button("Отменить");
        cancelButton.addClickListener(this);
       // cancelButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);

        deleteButton = new Button("Удалить");
        deleteButton.addClickListener(this);
        deleteButton.setVisible(false);
        addComponents(saveButton, cancelButton, deleteButton);
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton() == saveButton) {
            Notification.show("save button");
            try {
                binder.commit();
                if(owner instanceof FuncRequirementEditor) ((FuncRequirementEditor)owner).addCommitedContent();
            } catch (FieldGroup.CommitException e) {
                Notification.show("Ошибка в заполнении полей формы!");
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
                                    System.out.println("DELETE ID " + binder.getItemDataSource().getBean().getId());
                                    datasource.removeItem(binder.getItemDataSource().getBean().getId());

                                } catch (Exception e) {
                                    Notification.show("Ошибка удаления!\n"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
                                }
                                if(owner instanceof Window) ((Window)owner).close();
                            }
                        }
                    });
            return;
        } else if (event.getButton() == cancelButton) {
            binder.discard();
        }
        datasource.refresh();
        if(owner instanceof Window) ((Window)owner).close();

    }
}
