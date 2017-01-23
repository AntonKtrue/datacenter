package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
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
        setWidth("400");
    }


    public void edit(Integer id) {
        try {
            setCaption("Редактирование должности");
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
        bindingFields(new Agreementor());
        UI.getCurrent().addWindow(this);
    }


    private void bindingFields(Agreementor m) {
        m.setOrder(3);
        binder = new BeanFieldGroup<Agreementor>(Agreementor.class);
        binder.setItemDataSource(m);
        final SimpleJPAContainer<TransneftEmployee> employeesDs = new SimpleJPAContainer<TransneftEmployee>(TransneftEmployee.class);
        SimpleJPAContainer<TransneftRank> ranks = new SimpleJPAContainer<TransneftRank>(TransneftRank.class);
        ComboBox employee = new ComboBox("Сотрудник", employeesDs);
        final ComboBox rank = new ComboBox("Должность", ranks);
        employee.setContainerDataSource(employeesDs);
        employee.setImmediate(true);
        employee.setItemCaptionPropertyId("lastName");
        employee.setConverter(new SingleSelectConverter(employee));
        employee.setRequired(true);
        employee.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                TransneftEmployee selectedEmployee = employeesDs.getItem(event.getProperty().getValue()).getEntity();
                rank.setValue(selectedEmployee.getRank().getId());
            }
        });

        rank.setContainerDataSource(ranks);

        rank.setItemCaptionPropertyId("name");
        rank.setImmediate(true);
        rank.setConverter(new SingleSelectConverter(rank));
        rank.setRequired(true);
        binder.bind(rank, "rank");
        layout.addComponents(employee, rank);

        crudButtons = new CrudButtons(datasource, binder, this);
        layout.addComponent(crudButtons);
    }

}
