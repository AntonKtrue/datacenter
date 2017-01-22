package tn.kaz.ospas.view.funcrequirements;


import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import org.vaadin.easyuploads.FileFactory;
import org.vaadin.easyuploads.UploadField;

import tn.kaz.ospas.data.SimpleJPAContainer;
import tn.kaz.ospas.model.funcrequirement.Agreementor;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import tn.kaz.ospas.model.transneft.TransneftStructure;
import tn.kaz.ospas.view.CrudButtons;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by Anton on 20.01.2017.
 */
public class FuncRequirementEditor extends VerticalLayout {
    private TransneftStructure structure;
    private SimpleJPAContainer<FuncRequirement> funcRequirementDs;
    private BeanFieldGroup<FuncRequirement> binder;
    private FuncRequirement funcRequirement;
    private CrudButtons<FuncRequirement> crudButtons;
    private FormLayout layout;


    public FuncRequirementEditor(TransneftStructure structure, SimpleJPAContainer<FuncRequirement> funcRequirementDs ) {
        this.structure = structure;
        this.funcRequirementDs = funcRequirementDs;
        this.funcRequirement = new FuncRequirement(structure);
        buildFuncRequirementScreen();


    }

    public void addCommitedLabel() {
        Label addede = new Label("ФТ добавлен!");
        addComponent(addede);
//        final UploadField fileUpload = new UploadField();
//        fileUpload.setFieldType(UploadField.FieldType.FILE);
//        final File tempDir = new File("/tmp",String.valueOf(funcRequirement.getNumber()));
//
//        tempDir.mkdirs();
//
//
//        fileUpload.setCaption("Storagemode: " + tempDir + " , fieldType:"
//                + fileUpload.getFieldType());
//        fileUpload.setFileFactory(new FileFactory() {
//            @Override
//            public File createFile(String fileName, String fileType) {
//                File f = new File(tempDir, fileName);
//                return f;
//            }
//        });
//        addComponent(fileUpload);

//        LineBreakCounter lineBreakCounter = new LineBreakCounter();
//        lineBreakCounter.setSlow(true);
//
//        Upload sample = new Upload(null, lineBreakCounter);
//        final UploadInfoWindow uploadInfoWindow = new UploadInfoWindow(sample, lineBreakCounter);
//        sample.setImmediate(false);
//        sample.setButtonCaption("Upload File");
//        sample.addStartedListener(new Upload.StartedListener() {
//            @Override
//            public void uploadStarted(final Upload.StartedEvent event) {
//                if (uploadInfoWindow.getParent() == null) {
//                    uploadInfoWindow.setModal(true);
//                    uploadInfoWindow.setPosition(200,200);
//
//                    UI.getCurrent().addWindow(uploadInfoWindow);
//                }
//                uploadInfoWindow.setClosable(false);
//            }
//        });
//        sample.addFinishedListener(new Upload.FinishedListener() {
//            @Override
//            public void uploadFinished(final Upload.FinishedEvent event) {
//                uploadInfoWindow.setClosable(true);
//            }
//        });



        addComponent(new UploadExample());

    }


    private void buildFuncRequirementScreen() {
        layout = new FormLayout();
        Label objectName = new Label(structure.getName());
        layout.addComponent(objectName);
        binder = new BeanFieldGroup<FuncRequirement>(FuncRequirement.class);
        binder.setItemDataSource(funcRequirement);

        crudButtons = new CrudButtons<FuncRequirement>(funcRequirementDs, binder, this);
        layout.addComponent(crudButtons);
        Field<?> field = null;

        field = binder.buildAndBind("Номер", "number");
        field.setWidth("250");
        field.setRequired(true);

        layout.addComponent(field);

        TextArea shortDescription = new TextArea("Краткое описание (250 символов)");
        shortDescription.setRows(5);
        shortDescription.setColumns(50);
        binder.bind(shortDescription,"shortDescription");
        layout.addComponent(shortDescription);

        PopupDateField date = new PopupDateField("Дата ФТ:");
        date.setDateFormat("dd.MM.yyyy");
        date.setRequired(true);
        binder.bind(date,"date" );
        date.setValue(new Date());

        layout.addComponent(date);


        JPAContainer<Agreementor> agreementorsDs = new SimpleJPAContainer<Agreementor>(Agreementor.class);

        addComponent(layout);

    }

    private static class UploadInfoWindow extends Window implements
            Upload.StartedListener, Upload.ProgressListener,
            Upload.FailedListener, Upload.SucceededListener,
            Upload.FinishedListener {
        private final Label state = new Label();
        private final Label result = new Label();
        private final Label fileName = new Label();
        private final Label textualProgress = new Label();

        private final ProgressBar progressBar = new ProgressBar();
        private final Button cancelButton;
        private final LineBreakCounter counter;

        public UploadInfoWindow(final Upload upload,
                                final LineBreakCounter lineBreakCounter) {
            super("Status");
            this.counter = lineBreakCounter;

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

            cancelButton = new Button("Cancel");
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
        public void uploadFinished(final Upload.FinishedEvent event) {
            state.setValue("Idle");
            progressBar.setVisible(false);
            textualProgress.setVisible(false);
            cancelButton.setVisible(false);
        }

        @Override
        public void uploadStarted(final Upload.StartedEvent event) {
            // this method gets called immediately after upload is started
            progressBar.setValue(0f);
            progressBar.setVisible(true);
            UI.getCurrent().setPollInterval(500);
            textualProgress.setVisible(true);
            // updates to client
            state.setValue("Uploading");
            fileName.setValue(event.getFilename());

            cancelButton.setVisible(true);
        }

        @Override
        public void updateProgress(final long readBytes,
                                   final long contentLength) {
            // this method gets called several times during the update
            progressBar.setValue(new Float(readBytes / (float) contentLength));
            textualProgress.setValue("Processed " + readBytes + " bytes of "
                    + contentLength);
            //result.setValue(counter.getLineBreakCount() + " (counting...)");
        }

        @Override
        public void uploadSucceeded(final Upload.SucceededEvent event) {
            //result.setValue(counter.getLineBreakCount() + " (total)");
        }

        @Override
        public void uploadFailed(final Upload.FailedEvent event) {
//            result.setValue(counter.getLineBreakCount()
//                    + " (counting interrupted at "
//                    + Math.round(100 * progressBar.getValue()) + "%)");
        }
    }
    //file upload
    private static class LineBreakCounter implements Upload.Receiver {
        private int counter;
        private int total;
        private boolean sleep;

        /**
         * return an OutputStream that simply counts lineends
         */
        @Override
        public OutputStream receiveUpload(final String filename,
                                          final String MIMEType) {
            counter = 0;
            total = 0;
            return new OutputStream() {
                private static final int searchedByte = '\n';

                @Override
                public void write(final int b) throws IOException {
                    total++;
                    if (b == searchedByte) {
                        counter++;
                    }
                    if (sleep && total % 1000 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

        public int getLineBreakCount() {
            return counter;
        }

        public void setSlow(final boolean value) {
            sleep = value;
        }

    }

}
