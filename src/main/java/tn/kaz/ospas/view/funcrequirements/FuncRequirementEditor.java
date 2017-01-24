package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.ItemClickEvent;

import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.FileResource;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.Agreementor;

import tn.kaz.ospas.model.funcrequirement.FRCause;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;
import tn.kaz.ospas.view.funcrequirements.components.AgreementorWindow;
import tn.kaz.ospas.view.funcrequirements.components.OneToManyField;
import tn.kaz.ospas.view.funcrequirements.components.sortable.SortableLayout;
import tn.kaz.ospas.view.reports.ReportEditor;

import javax.persistence.Query;
import java.io.File;
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

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public FuncRequirementEditor(TransneftStructure structure, SimpleJPAContainer<FuncRequirement> funcRequirementDs ) {
        this.structure = structure;
        this.funcRequirementDs = funcRequirementDs;
        this.funcRequirement = new FuncRequirement(structure);
        Query query = funcRequirementDs.getEntityProvider().getEntityManager().createNamedQuery("Number.empty");
        List<Long> result  = query.getResultList();
        this.funcRequirement.setNumber(result.size() > 0 ? result.get(0) : 1);
        buildFuncRequirementScreen();
    }

    public FuncRequirementEditor(SimpleJPAContainer<FuncRequirement> funcRequirementDs, Object itemId) {
        this.funcRequirement = funcRequirementDs.getItem(itemId).getEntity();
        this.funcRequirementDs = funcRequirementDs;
        buildFuncRequirementScreen();
        addCommitedContent();
    }

    private void addAgreementorsArea() {
        final JPAContainer<Agreementor> agreementorsDs = JPAContainerFactory.make(Agreementor.class, Config.JPA_UNIT);
        agreementorsDs.addContainerFilter(new Compare.Equal("funcRequirement", funcRequirement));
        agreementorsDs.applyFilters();
        final OneToManyField<Agreementor> agreementors = new OneToManyField<Agreementor>(
                "Согласующие",
                binder,agreementorsDs ,
                new Object[]{"id","sequence", "employee", "rank", "department"},
                new String[]{"#","Порядок","Сотрудник","Должность","Отдел"},
                "agreementors"
        );
        agreementors.addListenerToAddButton(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
                window.create();
            }
        });
        agreementors.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                AgreementorWindow window = new AgreementorWindow(agreementorsDs, funcRequirement);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });
        //agreementors.getTable().setCellStyleGenerator(new Table.CellStyleGenerator());

        addComponent(agreementors);
    }
    private void addCauseArea() {
        final SimpleJPAContainer<FRCause> frCauseDs = new SimpleJPAContainer<FRCause>(FRCause.class);


        final RichTextArea causeRta = new RichTextArea("Основание доработки");
//        final SortableLayout sortableLayout = new SortableLayout();
//        sortableLayout.setSizeUndefined();
//        sortableLayout.setWidth("600px");
//        sortableLayout.addStyleName("no-horisontal-drag-hints");

        causeRta.setWidth(700f,Unit.PIXELS);
        Button show = new Button("debug show");
        show.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Notification.show(causeRta.getValue());
              //  Panel panel = new Panel();
              //  panel.setContent(new Label(causeRta.getValue(), ContentMode.HTML));
              //  sortableLayout.addComponent(panel);
                FRCause frCause = new FRCause(1,causeRta.getValue(),funcRequirement);
                frCauseDs.addEntity(frCause);
                frCauseDs.refresh();

            }
        });
        Table frCauseTable = new Table("cause table ",frCauseDs);

        frCauseTable.setContainerDataSource(frCauseDs);
        frCauseTable.setVisibleColumns(new Object[]{"sequence"});
        frCauseTable.addGeneratedColumn("", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                Property prop = source.getItem(itemId).getItemProperty(columnId);
//                Button btnView = new Button();
//                btnView.setData(itemId);
//                btnView.setDescription("Blalba:" + itemId);
//                btnView.setWidth("50px");
//
//                return btnView;
                //Panel panel = new Panel();
                //panel.setHeightUndefined();
                //panel.setWidth(500f,Unit.PIXELS);
                Label label = new Label(source.getItem(itemId).getItemProperty("description"),ContentMode.HTML);
                //panel.setContent(label);
                label.setHeightUndefined();
                label.setWidth(500f, Unit.PIXELS);
                return label;
            }
        });
        frCauseTable.setDragMode(Table.TableDragMode.ROW);
        frCauseTable.setDropHandler(new DropHandler() {
            @Override
            public void drop(DragAndDropEvent event) {
                //Transferable t = event.getTransferable();
                DataBoundTransferable t = (DataBoundTransferable) event.getTransferable();
                Container sourceContainer = t.getSourceContainer();

                Object sourceItemId = t.getData("itemId");

                AbstractSelect.AbstractSelectTargetDetails dropData = (AbstractSelect.AbstractSelectTargetDetails)event.getTargetDetails();
                Object targetItemId = dropData.getItemIdOver();


                Notification.show("Source: " + sourceItemId.toString() +
                        "; Target: " + targetItemId.toString() +
                        "; Location " + dropData.getDropLocation().toString());
//                switch(dropData.getDropLocation()) {
//                    case BOTTOM:
//                        //moveAfter(targetItemId, sourceItemId);
//
//                        break;
//                    case MIDDLE:
//                    case TOP:
//                        //final Object prevItemId = prevItemId(targetItemId);
//                        //moveAfter(prevItemId, sourceItemId);
//                        break;
//                }
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }
        });
        frCauseTable.setSortContainerPropertyId("sequence");
        frCauseTable.setSortEnabled(false);

        addComponent(causeRta);
        addComponent(show);
        addComponent(frCauseTable);
     //   addComponent(sortableLayout);
//        final JPAContainer<FRCause> frCauseDs = new SimpleJPAContainer<FRCause>(FRCause.class);
//        Table table = new Table("Основания доработки",frCauseDs);
//        table.setContainerDataSource(frCauseDs);
//
    }

    public void addCommitedContent() {
        //Label addede = new Label("ФТ добавлен!");
        //addComponent(addede);
        //addComponent(new FileUploader("Документ ФТ", 100000000l, Config.DOC_DIR, funcRequirement));

        if(funcRequirement.getFrFilePath() != null) {
            Link frFileLink = new Link("Документ ФТ " , new FileResource(new File(funcRequirement.getFrFilePath())));
            frFileLink.setTargetName("_blank");
            layout.addComponent(frFileLink);
        } else {
            addComponent(new FileUploader("Документ ФТ", 100000000l, Config.DOC_DIR, funcRequirement));
        }

        addAgreementorsArea();
        addCauseArea();







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
        Label objectName = new Label(funcRequirement.getStructure().getName());
        layout.addComponent(objectName);

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
