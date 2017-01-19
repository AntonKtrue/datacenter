package tn.kaz.ospas.view.directories.funcrequirement.notice;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.NoticeJPAContainer;
import tn.kaz.ospas.view.directories.funcrequirement.executors.ExecutorsTypesWindow;

/**
 * Created by Anton on 19.01.2017.
 */
public class NoticeTab extends VerticalLayout {

    private Table table;

    public NoticeTab() {

        final NoticeJPAContainer datasource = new NoticeJPAContainer();
        table = new NoticeTable(datasource);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                NoticeWindow window = new NoticeWindow(datasource);
                window.edit(Integer.valueOf(event.getItemId().toString()));
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        addComponent(content);

        VerticalLayout vertical = new VerticalLayout(noticeButtons(datasource), table);
        content.addComponent(vertical);
        content.setComponentAlignment(vertical, Alignment.MIDDLE_CENTER);

        addErrorHandle(content);

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

    private HorizontalLayout noticeButtons(final NoticeJPAContainer datasource) {
        Button addButton = new Button("Добавить");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                NoticeWindow window = new NoticeWindow(datasource);
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
