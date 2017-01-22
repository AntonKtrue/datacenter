package tn.kaz.ospas.view.funcrequirements.components;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.*;

/**
 * Created by user on 22.01.17.
 */
public class UploadDocument extends Upload {
    private Upload upload = new Upload();

    public UploadDocument(DocumentReceiver receiver) {
        upload.setImmediate(false);
        upload.setButtonCaption("Выберите файл");
        final UploadInfoWindow uploadInfoWindow = new UploadInfoWindow(upload, receiver);
        upload.addStartedListener(new StartedListener() {
            @Override
            public void uploadStarted(StartedEvent startedEvent) {
                if (uploadInfoWindow.getParent() == null) {
                    UI.getCurrent().addWindow(uploadInfoWindow);
                }
                uploadInfoWindow.setClosable(false);
            }
        });
        upload.addFinishedListener(new Upload.FinishedListener() {
            @Override
            public void uploadFinished(final FinishedEvent event) {
                uploadInfoWindow.setClosable(true);
            }
        });

    }

    @StyleSheet("uploadexmple.css")
    private static class UploadInfoWindow extends Window implements
            Upload.StartedListener, Upload.ProgressListener,
            Upload.FailedListener, Upload.SucceededListener,
            Upload.FinishedListener {
        private final Label state = new Label();
        private final Label result = new Label();
        private final Label fileName = new Label();
        private final Label textualProgress = new Label();

        private final ProgressBar progressBar = new ProgressBar();
        private final Button cancelButton = new Button("Отмена");
        private final DocumentReceiver receiver;

        public UploadInfoWindow(final Upload upload, DocumentReceiver receiver) {
            super("Статус");
            this.receiver = receiver;
            setWidth(350, Unit.PIXELS);

            addStyleName("upload-info");

            setResizable(false);
            setDraggable(false);

            final FormLayout l = new FormLayout();
            setContent(l);
            l.setMargin(true);

            final HorizontalLayout stateLayout = new HorizontalLayout();
            stateLayout.setSpacing(true);
            stateLayout.addComponent(state);
            cancelButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    upload.interruptUpload();
                }
            });
            cancelButton.setVisible(false);
            cancelButton.setStyleName("small");
            stateLayout.addComponent(cancelButton);

            stateLayout.setCaption("Current state");
            state.setValue("Idle");
            l.addComponent(stateLayout);

            fileName.setCaption("File name");
            l.addComponent(fileName);

            result.setCaption("Line breaks counted");
            l.addComponent(result);

            progressBar.setCaption("Progress");
            progressBar.setVisible(false);
            l.addComponent(progressBar);

            textualProgress.setVisible(false);
            l.addComponent(textualProgress);

            upload.addStartedListener(this);
            upload.addProgressListener(this);
            upload.addFailedListener(this);
            upload.addSucceededListener(this);
            upload.addFinishedListener(this);
        }

        @Override
        public void uploadFailed(FailedEvent failedEvent) {
            result.setValue("%)");
        }

        @Override
        public void uploadFinished(FinishedEvent finishedEvent) {
            state.setValue("Завершено");
            progressBar.setVisible(false);
            textualProgress.setVisible(false);
            cancelButton.setVisible(false);
        }

        @Override
        public void updateProgress(long readBytes, long contentLength) {

                progressBar.setValue(new Float(readBytes / (float) contentLength));
                textualProgress.setValue("Processed " + readBytes + " bytes of "
                        + contentLength);

        }

        @Override
        public void uploadStarted(StartedEvent startedEvent) {

            progressBar.setValue(0f);
            progressBar.setVisible(true);
            UI.getCurrent().setPollInterval(500);
            textualProgress.setVisible(true);

            state.setValue("Загрузка");
            fileName.setValue(startedEvent.getFilename());

            cancelButton.setVisible(true);

        }

        @Override
        public void uploadSucceeded(SucceededEvent succeededEvent) {
            result.setValue("Завершено" + receiver.getFileLocation() );
        }
    }


}
