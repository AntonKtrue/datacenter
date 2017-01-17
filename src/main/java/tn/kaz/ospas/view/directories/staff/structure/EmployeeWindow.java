package tn.kaz.ospas.view.directories.staff.structure;


import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.data.EmployeeJPAContainer;
import tn.kaz.ospas.data.RankJPAContainer;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftRank;

@SuppressWarnings("serial")
public class EmployeeWindow extends Window implements Button.ClickListener {

    private FormLayout layout;
    private BeanFieldGroup<TransneftEmployee> binder;
    private HorizontalLayout buttons;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;
    private ComboBox rankSelect;
    private Integer selectedRank;

    private EmployeeJPAContainer datasource;

    public EmployeeWindow(EmployeeJPAContainer datasource) {
        this.datasource = datasource;
        init();
        setModal(true);
    }

    private void init() {
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

        buttons = new HorizontalLayout();
        buttons.addComponent(saveButton);
        buttons.addComponent(cancelButton);
        buttons.addComponent(deleteButton);

        setContent(layout);

        setHeight("370");
        setWidth("400");
    }


    public void edit(Integer id) {
        try {
            setCaption("Редактирование должности");
            TransneftEmployee m = datasource.getItem(id).getEntity();
            bindingFields(m);
            deleteButton.setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {

            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create(TransneftDepartment department) {
        setCaption("Новая запись");
        bindingFields(new TransneftEmployee(department));
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(TransneftEmployee m) {
        binder = new BeanFieldGroup<TransneftEmployee>(TransneftEmployee.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Имя", "firstName");
        field.setWidth("200");
        layout.addComponent(field);

        field = binder.buildAndBind("Фамилия", "lastName");
        field.setWidth("200");
        layout.addComponent(field);

        field = binder.buildAndBind("Отчество", "patroName");
        field.setWidth("200");
        layout.addComponent(field);


        rankSelect = new ComboBox("Должность", new RankJPAContainer());
        rankSelect.setItemCaptionPropertyId("name");
        rankSelect.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                selectedRank = (Integer) event.getProperty().getValue();
            }
        });
        layout.addComponent(rankSelect);

        layout.addComponent(buttons);
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton() == saveButton) {
            try {
                binder.getItemDataSource().getBean().setRank(selectedRank);
                binder.commit();
            } catch (FieldGroup.CommitException e) {
                Notification.show("Возникла ошибка 2!");
                return;
            }

            try {
                datasource.addEntity(binder.getItemDataSource().getBean());
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
                                    //log.debug("Excluiu a Mercadoria!");
                                } catch (Exception e) {
                                    //log.debug("Não consegui remover a Mercadoria!",e);
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
