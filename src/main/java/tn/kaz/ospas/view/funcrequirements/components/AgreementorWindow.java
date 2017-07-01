package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftRank;
import tn.kaz.ospas.view.CrudButtons;

/**
 * Created by Anton on 23.01.2017.
 */
public class AgreementorWindow extends Window {

    private FormLayout layout;
    private BeanFieldGroup<Agreementor> binder;
    private JPAContainer<Agreementor> datasource;
    private CrudButtons<Agreementor> crudButtons;
    private FuncRequirement funcRequirement;

    public AgreementorWindow(JPAContainer<Agreementor> datasource, FuncRequirement funcRequirement) {
        this.datasource = datasource;
        this.funcRequirement = funcRequirement;
        init();
        setModal(true);
    }

    private void init() {
        layout = new FormLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        setContent(layout);
        setHeight("370");
        setWidth("800");
    }

    public void edit(Integer id) {
        try {
            setCaption("Добавление согласования");
            Agreementor m = datasource.getItem(id).getEntity();
            bindingFields(m);
            crudButtons.getDeleteButton().setVisible(true);
            UI.getCurrent().addWindow(this);
        } catch (Exception ex) {
            Notification.show("Возникла ошибка 1! Обратитесь к разработчикам\n"+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }


    public void create() {
        setCaption("Новая запись");
        Agreementor agreementor = new Agreementor();
        agreementor.setSequence(datasource.size() + 1);
        agreementor.setFuncRequirement(funcRequirement);
        bindingFields(agreementor);
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(Agreementor agreementor) {

        binder = new BeanFieldGroup<Agreementor>(Agreementor.class);
        binder.setItemDataSource(agreementor);
        final SimpleJPAContainer<TransneftEmployee> employeesDs = new SimpleJPAContainer<TransneftEmployee>(TransneftEmployee.class);
        SimpleJPAContainer<TransneftRank> ranksDs = new SimpleJPAContainer<TransneftRank>(TransneftRank.class);
        SimpleJPAContainer<TransneftDepartment> departmentsDs = new SimpleJPAContainer<TransneftDepartment>(TransneftDepartment.class);

        ComboBox employee = new ManyToOneComboBox("Сотрудник",employeesDs,"caption", true);
        final ComboBox rank = new ManyToOneComboBox("Должность", ranksDs, "name", true);
        final ComboBox department = new ManyToOneComboBox("Отдел",departmentsDs, "name", true);
        employee.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                TransneftEmployee selectedEmployee = employeesDs.getItem(event.getProperty().getValue()).getEntity();
                rank.setValue(selectedEmployee.getRank().getId());
                department.setValue(selectedEmployee.getDepartment().getId());
            }
        });
        binder.bind(employee, "employee");
        binder.bind(rank, "rank");
        binder.bind(department, "department");
        layout.addComponents(employee, rank, department);


        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }

}
