package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.data.HierarchicalDepartmentContainer;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftStructure;

/**
 * Created by Anton on 17.01.2017.
 */
public abstract class CRUDWindow<T> extends Window implements Button.ClickListener {

    private FormLayout layout;
    private BeanFieldGroup<T> binder;
    private HorizontalLayout buttons;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;
    private Class<T> clazz;


    private JPAContainer<T> datasource;

    public CRUDWindow(JPAContainer<T> datasource, Class<T> clazz) {
        this.datasource = datasource;
        this.clazz = clazz;
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
            T m = datasource.getItem(id).getEntity();
            bindingFields(m);
            deleteButton.setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


//    public void create() {
//        setCaption("Новая запись");
//        UI.getCurrent().addWindow(this);
//    }

    abstract public void bindingFields(T m);

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
                                  //  datasource.removeItem(binder.getItemDataSource().getBean().getId());
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
