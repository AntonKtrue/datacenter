package tn.kaz.ospas.view.directories.staff.ranks;

import tn.kaz.ospas.data.RankJPAContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * Created by user on 11.01.17.
 */
public class RankTab extends VerticalLayout {

    private Table table;


    public RankTab() {
        //setLocale(new Locale("pt", "BR"));
        final RankJPAContainer datasource = new RankJPAContainer();
        table = new RankTable(datasource);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                RankWindow window = new RankWindow(datasource);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);

        VerticalLayout vertical = putAllOnVertical(table,
                buildBarButtons(datasource));
        content.addComponent(vertical);
        content.setComponentAlignment(vertical, Alignment.MIDDLE_CENTER);

        addErrorHandle(content);

    }

    private VerticalLayout putAllOnVertical(Component... components) {
        VerticalLayout vertical = new VerticalLayout();
        for (Component c: components) {
            if (c != null) {
                vertical.addComponent(c);
                vertical.setComponentAlignment(c, Alignment.MIDDLE_CENTER);
            }
        }
        return vertical;
    }

    private HorizontalLayout buildBarButtons(final RankJPAContainer datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                RankWindow window = new RankWindow(datasource);
                window.create();
            }
        });

        Button refreshButton = new Button("Обновить");
        refreshButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                datasource.refresh();
            }
        });



        Button[] buttons = {addButton, refreshButton}; //, bSobre};
        HorizontalLayout barButton = new HorizontalLayout();
        barButton.setHeight("50");

        for (Button b: buttons) {
            b.setStyleName(Runo.BUTTON_BIG);
            barButton.addComponent(b);
            barButton.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
        }

        return barButton;
    }



    private void addErrorHandle(final VerticalLayout content) {
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "Произошла ошибка!:\n";
                for (Throwable t = event.getThrowable(); t != null;
                     t = t.getCause())
                    if (t.getCause() == null)
                        cause += t.getClass().getName() + "\n";

                Notification.show(cause, Type.ERROR_MESSAGE);

                doDefault(event);
            }
        });
    }
}
