package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;

/**
 * Created by Anton on 20.01.2017.
 */
public class CreateVerifyWindow extends Window implements Button.ClickListener{
    private boolean flag;
    private Button createButton;
    private Button cancelButton;
    private String text;
    private TextArea shortDesc;
    public CreateVerifyWindow() {
        FormLayout layout = new FormLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        setContent(layout);
        setHeight("200");
        setWidth("800");

        shortDesc = new TextArea();
        shortDesc.setRows(3);
        shortDesc.setColumns(20);
        shortDesc.setCaption("Краткое описание");
        layout.addComponent(shortDesc);
        createButton = new Button("Создать");
        cancelButton = new Button("Отмена");
        createButton.addClickListener(this);
        cancelButton.addClickListener(this);
        HorizontalLayout buttons = new HorizontalLayout(createButton, cancelButton);
        layout.addComponent(buttons);

        setModal(true);
        UI.getCurrent().addWindow(this);
    }

    public boolean createNewObject() {
        return flag;
    }

    public String getText() {
        return text;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton() == createButton) {
            flag = true;
            text = shortDesc.getInputPrompt();
        }  else if (event.getButton() == cancelButton) {
           flag = false;
        }
       close();
    }
}
