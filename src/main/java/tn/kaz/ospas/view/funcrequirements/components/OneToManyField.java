package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

/**
 * Created by Anton on 23.01.2017.
 */
public class OneToManyField<T> extends Panel {

    private String caption;
    private BeanFieldGroup<FuncRequirement> binder;
    private JPAContainer<T> datasource;
    private Object[] columns;
    private String[] headers;
    private String property;

    public OneToManyField(String caption, BeanFieldGroup<FuncRequirement> binder, JPAContainer<T> datasource, Object[] columns, String[] headers, String property) {
        this.caption = caption;
        this.binder = binder;
        this.datasource = datasource;
        this.columns = columns;
        this.headers = headers;
        this.property = property;
        init();
        setWidth(500f,Unit.PIXELS);
        setHeight(300f,Unit.PIXELS);
    }

    private void init() {
        VerticalLayout layout = new VerticalLayout();
        Table table = new Table(caption);
       // JPAContainer<Agreementor> agreementorsDs = new SimpleJPAContainer<Agreementor>(Agreementor.class);
        table.setContainerDataSource(datasource);
        table.setSizeFull();
        table.setVisibleColumns(columns);
        table.setColumnHeaders(headers);
        binder.bind(table,property);
        layout.addComponent(table);
        setContent(layout);
    }
}
