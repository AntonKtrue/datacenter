package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by user on 22.01.17.
 */
@SuppressWarnings("serial")
public class FileReceiver implements Receiver, SucceededListener {

    public File file;

    @Override
    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        FileOutputStream fos = null; // Stream to write to
        try {

            file = new File("/tmp/uploads/" + filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            new Notification("Could not open file<br/>",
                    e.getMessage(),
                    Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());
            return null;
        }
        return fos;
    }

    @Override
    public void uploadSucceeded(SucceededEvent succeededEvent) {

    }
}
