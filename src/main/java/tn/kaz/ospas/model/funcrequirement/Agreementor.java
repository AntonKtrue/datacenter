package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.transneft.TransneftEmployee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_agreementor")
public class Agreementor extends Signatory  {
    @ManyToOne(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    @NotNull
    private int sequence;
    @Override
    public int getSequence() {
        return sequence;
    }
    @Override
    public void setSequence(int order) {
        this.sequence = order;
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public Agreementor(TransneftEmployee employee, FuncRequirement funcRequirement, int sequence) {
        super(employee);
        this.funcRequirement = funcRequirement;
        this.sequence = sequence;
    }

    public Agreementor(FuncRequirement funcRequirement, int sequence) {
        this.funcRequirement = funcRequirement;
        this.sequence = sequence;
    }

    public Agreementor() {
    }
}
