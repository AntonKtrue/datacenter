package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_description")
public class Description extends Identity implements Sequenceable {
    @NotNull
    private int sequence;
    @NotNull
    private String description;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Description(int sequence, String description) {
        this.sequence = sequence;
        this.description = description;
    }

    public Description() {
        this(0,"");
    }

    @Override
    public int getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(int order) {
        this.sequence = order;
    }




}
