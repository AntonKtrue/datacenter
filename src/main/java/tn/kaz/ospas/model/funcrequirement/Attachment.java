package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;

import javax.persistence.*;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_attachment")
public class Attachment extends Identity {
    private String name;
    private String filePath;

    @ManyToOne(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private FRCause cause;

    public FRCause getCause() {
        return cause;
    }

    public void setCause(FRCause cause) {
        this.cause = cause;
    }

    public Attachment() {
        this("", "");
    }

    public Attachment(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
