package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.ElementCollectionEditor;
import com.vaadin.addon.jpacontainer.fieldfactory.EmbeddableEditor;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.MasterDetailEditor;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.AbstractBeanContainer;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.*;
import org.vaadin.viritin.fields.ElementCollectionTable;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftStructure;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;

/**
 * Created by Anton on 20.01.2017.
 */
public class FuncRequirementEditor extends VerticalLayout {
    private TransneftStructure structure;
    private SimpleJPAContainer<FuncRequirement> funcRequirementDs;
    private BeanFieldGroup<FuncRequirement> binder;
    private FuncRequirement funcRequirement;


    public FuncRequirementEditor(TransneftStructure structure, SimpleJPAContainer<FuncRequirement> funcRequirementDs ) {
        this.structure = structure;
        this.funcRequirementDs = funcRequirementDs;
        this.funcRequirement = new FuncRequirement(structure);
        buildFuncRequirementScreen();


    }

    private void buildFuncRequirementScreen() {
        FormLayout layout = new FormLayout();
        Label objectName = new Label(structure.getName());
        layout.addComponent(objectName);
        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);
        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("140");
        layout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(50);
        binder.bind(shortDescription,"shortDescription");
        layout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        date.setDateFormat("dd.MM.yyyy");
        binder.bind(date,"date" );
        layout.addComponent(date);

//        Panel panel = new Panel();
//        panel.setWidth(400f,Unit.PIXELS);
//        panel.setHeight(300f, Unit.PIXELS);
//        Label panelLabel = new Label("this is a panel");
//        panel.setContent(panelLabel);
//        layout.addComponent(panel);
        JPAContainer<Agreementor> agreementorJPAContainer = new SimpleJPAContainer<Agreementor>(Agreementor.class);
        agreementorJPAContainer.setApplyFiltersImmediately(false);
        agreementorJPAContainer.addContainerFilter(new Compare.Equal("funcRequirement",funcRequirement));
        agreementorJPAContainer.applyFilters();

        Table table = new Table("Agreementors");
        table.setContainerDataSource(agreementorJPAContainer);
        table.setVisibleColumns("id","order");
        table.setColumnHeaders("#","Порядок");
        layout.addComponent(table);

        addComponent(layout);


    }
}
