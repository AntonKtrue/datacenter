package tn.kaz.ospas.view.funcrequirements;


import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Anton on 23.01.2017.
 */
@SuppressWarnings("serial")
public class FileUploader extends HorizontalLayout {
    final Label succededLabel = new Label("Загрузка завершена");
    final private FuncRequirement funcRequirement;
    final private SimpleJPAContainer<FuncRequirement> funcRequirementDs;

    private Long limit;
    private String filename;
    private File uploads;
    private String filePathProperty;

    private String caption;
    private String uploadDir;

    private HorizontalLayout uploadField, uploadedField;

    public FileUploader(String caption,
                        final Long limit,
                        String uploadDir,
                        FuncRequirement funcRequirement,
                        SimpleJPAContainer<FuncRequirement> funcRequirementDs,
                        String filePathProperty
                        ) {

        this.limit = limit;
        this.uploadDir = uploadDir;
        this.caption = caption;

        this.filePathProperty = filePathProperty;
        this.funcRequirement = funcRequirement;
        this.funcRequirementDs = funcRequirementDs;
        if (funcRequirementDs.getItem(funcRequirement.getId()).getItemProperty(filePathProperty).getValue() != null) {
            uploadedField = makeUploadedField();
            addComponent(uploadedField);
        } else {
            uploadField = makeUploadField();
            addComponent(uploadField);
        }
    }

    private HorizontalLayout makeUploadField() {
        HorizontalLayout layout = new HorizontalLayout();
        FileReceiver receiver = new FileReceiver();
        final Upload upload = new Upload(caption, receiver);
        final ProgressBar progress = new ProgressBar(0.0f);
        progress.setVisible(false);
        upload.setButtonCaption(null);
        upload.addChangeListener(new Upload.ChangeListener()
        {
            @Override
            public void filenameChanged(Upload.ChangeEvent event)
            {
                if (event.getFilename() != null)
                    upload.setButtonCaption("Загрузить");
            }
        });


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
        succededLabel.setVisible(false);

        layout.addComponents(succededLabel, upload, progress);
        uploads = new File(uploadDir, funcRequirement.generateDocPath());
        if (!uploads.exists() && !uploads.mkdirs())
            addComponent(new Label("ERROR: Could not create upload dir"));
        return layout;
    }

    private HorizontalLayout makeUploadedField() {
        //   if(funcRequirement.getFrFilePath() != null) {
        HorizontalLayout layout = new HorizontalLayout();
        Link frFileLink = new Link(caption, new FileResource(new File(funcRequirementDs.getItem(funcRequirement.getId()).getItemProperty(filePathProperty).getValue().toString())));
        frFileLink.setTargetName("_blank");
        Button deleteButton = new Button("Удалить");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                funcRequirementDs.getItem(funcRequirement.getId()).getItemProperty(filePathProperty).setValue(null);
                try {
                    Files.delete(Paths.get(funcRequirementDs.getItem(funcRequirement.getId()).getItemProperty(filePathProperty).getValue().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                    Notification.show("Ошибка удаления файла");
                } finally {
                    removeComponent(uploadedField);
                    uploadField = makeUploadField();
                    addComponent(uploadField);
                }
            }
        });
        layout.addComponents(deleteButton, frFileLink);
        return layout;


    }

    @SuppressWarnings("serial")
    private class FileReceiver implements Upload.Receiver, Upload.SucceededListener {
        private File file;
        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            //Notification.show(filename);
            //Notification.show(mimeType);

            if(filename == null) return null;
            FileUploader.this.filename = filename;
            FileOutputStream fos = null;
            try {
                file = new File(uploads, filename);
                fos = new FileOutputStream(file);
            } catch (final java.io.FileNotFoundException e) {
//                new Notification("Could not open file<br/>",
//                        e.getMessage(),
//                        Notification.Type.ERROR_MESSAGE)
//                        .show(Page.getCurrent());
                return null;
            }
            return fos;
        }

        public void uploadSucceeded(Upload.SucceededEvent event) {
            succededLabel.setVisible(true);
            funcRequirementDs.getItem(funcRequirement.getId()).getItemProperty(filePathProperty).setValue(file.getAbsoluteFile());
            funcRequirementDs.commit();
            funcRequirementDs.refresh();
            removeComponent(uploadField);
            uploadedField = makeUploadedField();
            addComponent(uploadedField);
        }
    }
}
