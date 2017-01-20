package tn.kaz.ospas.view.directories.funcrequirement.executors;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.executors.ExecutorType;

/**
 * Created by Anton on 19.01.2017.
 */
public class ExecutorTypesTab extends VerticalLayout {

    private Table table;

    public ExecutorTypesTab() {

        final SimpleJPAContainer<ExecutorType> datasource = new SimpleJPAContainer<ExecutorType>(ExecutorType.class);
        table = new ExecutorsTypesTable(datasource);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                ExecutorsTypesWindow window = new ExecutorsTypesWindow(datasource);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);

        VerticalLayout vertical = new VerticalLayout(executorTypeButtons(datasource), table);
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



    private void addErrorHandle(final VerticalLayout content) {
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "Произошла ошибка!:\n";
                for (Throwable t = event.getThrowable(); t != null;
                     t = t.getCause())
                    if (t.getCause() == null)
                        cause += t.getClass().getName() + "\n";

                Notification.show(cause, Notification.Type.ERROR_MESSAGE);

                doDefault(event);
            }
        });
    }

    private HorizontalLayout executorTypeButtons(final SimpleJPAContainer<ExecutorType> datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ExecutorsTypesWindow window = new ExecutorsTypesWindow(datasource);
                window.create();
            }
        });

        Button refreshButton = new Button("Обновить");
        refreshButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                datasource.refresh();
            }
        });


        HorizontalLayout barButton = new HorizontalLayout(addButton, refreshButton);
        barButton.setHeight("50");
        return barButton;
    }

}
