package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import tn.kaz.ospas.data.EmployeeJPAContainer;
import tn.kaz.ospas.data.RankJPAContainer;
import tn.kaz.ospas.model.transneft.*;

/**
 * Created by user on 18.01.17.
 */
public class EmployeeWindow extends Window implements Button.ClickListener{
    private FormLayout layout;
    private BeanFieldGroup<TransneftEmployee> binder;
    private HorizontalLayout buttons;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;
    private RankJPAContainer ranks;
    private EmployeeJPAContainer datasource;

    public EmployeeWindow(EmployeeJPAContainer datasource, RankJPAContainer ranks) {
        this.datasource = datasource;
        this.ranks = ranks;
        init();
        setModal(true);
    }


    protected void init() {
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
        TransneftEmployee ts = new TransneftEmployee(department);
//        if(ts.getType() == StructureType.OST
//                || ts.getType() == StructureType.UMN
//                || ts.getType() == StructureType.OBJ) {
//            try {
//                EntityManager em = datasource.getEntityProvider().getEntityManager();
//                Query query = em.createQuery("Select MAX(s.code) from TransneftStructure s where s.type = '" +
//                        ts.getType() + "' and s.parent = " + ts.getParentCodedId().getName());
//                String newCode = String.format("%02d", Integer.parseInt((String)query.getSingleResult()) + 1);
//                ts.setCode(newCode);
//            } catch (Exception ignored) {
//                ignored.printStackTrace();
//            }
//        }
        bindingFields(ts);
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(TransneftEmployee m) {
        binder = new BeanFieldGroup<TransneftEmployee>(TransneftEmployee.class);
        binder.setItemDataSource(m);
        Field<?> field = null;
        field = binder.buildAndBind("Имя", "firstName");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Фамилия", "lastName");
        field.setWidth("250");
        layout.addComponent(field);

        field = binder.buildAndBind("Отчество", "patroName");
        field.setWidth("250");
        layout.addComponent(field);

//        field = binder.buildAndBind("Должность", "rank", ComboBox.class);
//        field.setWidth("250");
//        layout.addComponent(field);
        ComboBox rank = new ComboBox("Должность", ranks);
        rank.setContainerDataSource(ranks);
        rank.setItemCaptionPropertyId("name");
        rank.setImmediate(true);
        rank.setConverter(new SingleSelectConverter(rank));
        binder.bind(rank, "rank");
        layout.addComponent(rank);

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
