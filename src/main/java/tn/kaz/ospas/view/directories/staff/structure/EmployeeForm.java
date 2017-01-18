package tn.kaz.ospas.view.directories.staff.structure;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;

import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftRank;

/**
 * Created by user on 18.01.17.
 */
public class EmployeeForm extends Window implements Button.ClickListener {

    private TextField firstName;
    private TextField lastName;
    private TextField patroName;
    private ComboBox rank;
    private Button okButton, discardButton;
    private BeanFieldGroup<TransneftEmployee> fieldGroup;
    private TransneftEmployee editedEmployee;
    private JPAContainer<TransneftRank> ranks;

    public EmployeeForm(TransneftEmployee editedEmployee, JPAContainer<TransneftRank> ranks) {
        this.editedEmployee = editedEmployee;
        this.ranks = ranks;
        init();
        setModal(true);
    }

    protected void init() {
        firstName = new TextField("Имя");
        lastName = new TextField("Фамилия");
        patroName = new TextField("Отчество");
        rank = new ComboBox("Должность");
        rank.setContainerDataSource(ranks);
        rank.setItemCaptionPropertyId("name");
        FormLayout layout = new FormLayout(firstName, lastName, patroName, rank);

        okButton = new Button("Ok");
        okButton.addClickListener(this);
        discardButton = new Button("Discard");
        discardButton.addClickListener(this);
        HorizontalLayout buttonBar = new HorizontalLayout(okButton, discardButton);
        layout.addComponent(buttonBar);

        fieldGroup = new BeanFieldGroup<TransneftEmployee>(TransneftEmployee.class);
        fieldGroup.setItemDataSource(editedEmployee);
        fieldGroup.bindMemberFields(this);

        setContent(layout);
        setHeight("470");
        setWidth("600");
    }

    public void create() {
        setCaption("Новая запись");
     //   TransneftStructure ts = new TransneftStructure(selectedStructure);
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
      //  bindingFields(ts);
        UI.getCurrent().addWindow(this);
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        if (clickEvent.getSource() == okButton) {
            try {
                fieldGroup.commit();
                Notification.show("Edited employee: "
                                + fieldGroup.getItemDataSource().getBean(),
                        Notification.Type.TRAY_NOTIFICATION);
            } catch (FieldGroup.CommitException e) {
                Notification.show("Validation failed: " + e.toString()
                                + "Unable to commit input."
                        , Notification.Type.ERROR_MESSAGE);
            }
        } else {
            fieldGroup.discard();
        }
    }
}
