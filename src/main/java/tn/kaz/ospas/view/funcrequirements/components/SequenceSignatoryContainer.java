package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.ui.*;
import tn.kaz.ospas.data.HierarchicalJPAContainer;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;
import tn.kaz.ospas.model.funcrequirement.Sequenceable;
import tn.kaz.ospas.model.funcrequirement.Signatory;
import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.directories.staff.structure.StructureTree;

/**
 * Created by Anton on 26.01.2017.
 */
public class SequenceSignatoryContainer extends VerticalLayout {

    private SimpleJPAContainer<? extends Signatory> datasource;
    private HierarchicalJPAContainer<TransneftStructure> structureDs;
    private Table table;
    private String caption;
    private FuncRequirement funcRequirement;

    public SequenceSignatoryContainer(String caption, SimpleJPAContainer<? extends Signatory> signatoryJs, HierarchicalJPAContainer<TransneftStructure> structureDs, FuncRequirement funcRequirement) {
        this.datasource = datasource;
        this.caption = caption;
        this.funcRequirement = funcRequirement;
        this.structureDs = structureDs;

        Button addButton = new Button("Добавить");
        Button delButton = new Button("Удалить");
        HorizontalLayout buttons = new HorizontalLayout(addButton, delButton);
        addComponents(buttons);
        setCaption(caption);
    }

    private class AddEmployeeWindow extends Window {
        StructureTree tree = new StructureTree(structureDs);


    }
}
