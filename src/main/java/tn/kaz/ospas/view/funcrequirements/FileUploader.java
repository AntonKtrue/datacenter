package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Anton on 23.01.2017.
 */
@SuppressWarnings("serial")
public class FileUploader extends VerticalLayout {
    final Label succededLabel = new Label("Загрузка завершена");
    final private FuncRequirement funcRequirement;
    final Upload upload;
    private Long limit;
    private String filename;
    private File uploads;

    public FileUploader(String caption, final Long limit, String uploadDir, FuncRequirement funcRequirement) {
        this.limit = limit;
        this.funcRequirement = funcRequirement;
        succededLabel.setVisible(false);
        addComponent(succededLabel);
        FileReceiver receiver = new FileReceiver();
        upload = new Upload(caption, receiver);
        final ProgressBar progress = new ProgressBar(0.0f);
        progress.setVisible(false);
        upload.setButtonCaption("Загрузить");
        upload.addSucceededListener(receiver);
        upload.addStartedListener(new Upload.StartedListener() {
            private static final long serialVersionUID = 4728847902678459488L;

            @Override
            public void uploadStarted(Upload.StartedEvent event) {
                if (event.getContentLength() > limit) {
                    Notification.show("Файл слишком большой",
                            Notification.Type.ERROR_MESSAGE);
                    upload.interruptUpload();
                }
            }
        });

        upload.addProgressListener(new Upload.ProgressListener() {
            private static final long serialVersionUID = 8587352676703174995L;

            @Override
            public void updateProgress(long readBytes, long contentLength) {
                if (readBytes > limit) {
                    Notification.show("Файл слишком большой",
                            Notification.Type.ERROR_MESSAGE);
                    upload.interruptUpload();
                }
                progress.setVisible(true);
                if (contentLength == -1)
                    progress.setIndeterminate(true);
                else {
                    progress.setIndeterminate(false);
                    progress.setValue(((float) readBytes) /
                            ((float) contentLength));
                }
            }
        });
        addComponents(upload, progress);
        uploads = new File(uploadDir, funcRequirement.generateDocPath() );
        if (!uploads.exists() && !uploads.mkdirs())
            addComponent(new Label("ERROR: Could not create upload dir"));
    }

    @SuppressWarnings("serial")
    private class FileReceiver implements Upload.Receiver, Upload.SucceededListener {
        private File file;
        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            FileUploader.this.filename = filename;
            // Create upload stream
            FileOutputStream fos = null; // Stream to write to
            try {
                // Open the file for writing.
                file = new File(uploads, filename);
                fos = new FileOutputStream(file);
            } catch (final java.io.FileNotFoundException e) {
                new Notification("Could not open file<br/>",
                        e.getMessage(),
                        Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
                return null;
            }
            return fos; // Return the output stream to write to
        }

        public void uploadSucceeded(Upload.SucceededEvent event) {
            // Show the uploaded file in the image viewer
           // image.setVisible(true);
          //  image.setSource(new FileResource(file));
            succededLabel.setVisible(true);
            funcRequirement.setFrFilePath(file.getAbsolutePath());
            upload.setVisible(false);

        }
    }
}
