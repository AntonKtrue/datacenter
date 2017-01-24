package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_cause")
public class FRCause extends Identity implements Sequenceable {
    @NotNull
    private int sequence;
    @NotNull
    private String description;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cause", fetch = FetchType.LAZY)
    private Set<Attachment> attachments;

    @Override
    public int getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(int order) {
        this.sequence = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public FRCause() {
    }

    public FRCause(int sequence, String description, FuncRequirement funcRequirement) {
        this.sequence = sequence;
        this.description = description;
        this.funcRequirement = funcRequirement;
    }
}
