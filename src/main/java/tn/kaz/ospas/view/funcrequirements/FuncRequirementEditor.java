package tn.kaz.ospas.view.funcrequirements;


import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import org.vaadin.easyuploads.FileFactory;
import org.vaadin.easyuploads.UploadField;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;

import java.io.File;

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




    }




    private void buildFuncRequirementScreen() {
        layout = new FormLayout();
        Label objectName = new Label(structure.getName());
        layout.addComponent(objectName);
        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);
        crudButtons = new CrudButtons<FuncRequirement>(funcRequirementDs, binder, this);
        layout.addComponent(crudButtons);
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

        JPAContainer<Agreementor> agreementorsDs = new SimpleJPAContainer<Agreementor>(Agreementor.class);

        addComponent(layout);


    }
}
