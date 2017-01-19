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
public class FRCause extends Identity {
    @NotNull
    private int order;
    @NotNull
    private String description;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    @OneToMany
    private Set<Attachment> attachments;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
}
