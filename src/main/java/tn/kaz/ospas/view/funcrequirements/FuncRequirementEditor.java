package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.funcrequirement.TopAgreementor;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;
import tn.kaz.ospas.view.funcrequirements.components.AgreementorWindow;
import tn.kaz.ospas.view.funcrequirements.components.OneToManyField;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton on 20.01.2017.
 */
public class FuncRequirementEditor extends VerticalLayout {
    private TransneftStructure structure;
    private SimpleJPAContainer<FuncRequirement> funcRequirementDs;
    private BeanFieldGroup<FuncRequirement> binder;
    private FuncRequirement funcRequirement;
    private CrudButtons<FuncRequirement> crudButtons;
    private FormLayout layout;

    public FuncRequirementEditor(TransneftStructure structure, SimpleJPAContainer<FuncRequirement> funcRequirementDs ) {
        this.structure = structure;
        this.funcRequirementDs = funcRequirementDs;
        this.funcRequirement = new FuncRequirement(structure);
        buildFuncRequirementScreen();
    }

    public void addCommitedLabel() {
        Label addede = new Label("ФТ добавлен!");
        addComponent(addede);
       // addComponent(layout);
        addComponent(new FileUploader("Документ ФТ", 100000000l, Config.DOC_DIR, funcRequirement));
//        field = binder.buildAndBind("Согласующие","agreementors");
//        field.setWidth("600");
//        layout.addComponent(field);

//        final JPAContainer<Agreementor> agreementorsDs = JPAContainerFactory.make(Agreementor.class, Config.JPA_UNIT);
//        agreementorsDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
//        agreementorsDs.applyFilters();
//        OneToManyField<Agreementor> agreementors = new OneToManyField<Agreementor>(
//                "Согласующие",
//                binder,agreementorsDs ,
//                new Object[]{"id","order", "employee", "rank", "department"},
//                new String[]{"#","Порядок","Сотрудник","Должность","Отдел"},
//                "agreementors"
//        );
//        agreementors.addListenerToAddButton(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
//                window.create();
//            }
//        });
//       addComponent(agreementors);



//        JPAContainer<TopAgreementor> topAgreementorsDs = new SimpleJPAContainer<TopAgreementor>(TopAgreementor.class);
//        Panel topAgreementors = new OneToManyField<TopAgreementor>(
//                "Согласующие в шапке",
//                binder, funcRequirementDs,
//                new Object[]{"id","order", "employee", "rank", "department"},
//                new String[]{"#","Порядок","Сотрудник","Должность","Отдел"},
//                "topAgreementors"
//        );
//        addComponent(topAgreementors);
    }


    private void buildFuncRequirementScreen() {
        layout = new FormLayout();
        Label objectName = new Label(structure.getName());
        layout.addComponent(objectName);
        Query query = funcRequirementDs.getEntityProvider().getEntityManager().createNamedQuery("Number.empty");
        List<Long> result  = query.getResultList();
        funcRequirement.setNumber(result.get(0));
        System.out.println("NEW NUMBER IS " + funcRequirement.getNumber());
        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);


        crudButtons = new CrudButtons<FuncRequirement>(funcRequirementDs, binder, this);
        layout.addComponent(crudButtons);
        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("250");
        field.setRequired(true);

        layout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(40);
        shortDescription.setNullRepresentation("");
        binder.bind(shortDescription,"shortDescription");
        layout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        date.setDateFormat("dd.MM.yyyy");
        date.setRequired(true);
        binder.bind(date,"date" );
        date.setValue(new Date());

        layout.addComponent(date);
        addComponent(layout);
       // final SimpleJPAContainer<Agreementor> agreementorsDs = JPAContainerFactory.make() //new SimpleJPAContainer<Agreementor>(Agreementor.class);
    }


}
