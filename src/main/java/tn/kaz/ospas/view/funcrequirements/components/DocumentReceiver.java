package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by user on 22.01.17.
 */
public class DocumentReceiver implements Upload.Receiver {
    public File file;
    @Override
    public OutputStream receiveUpload(String filename, String MIMEType) {
        FileOutputStream fos = null;
        try {
            file = new File("/tmp/uploads/" + filename);
            fos = new FileOutputStream(file);
        } catch (final FileNotFoundException e) {
            new Notification("CouldNotOpenFile<br/>",e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        }
        return fos;
    }

    public String getFileLocation() {
        return file.getAbsolutePath();
    }


}
