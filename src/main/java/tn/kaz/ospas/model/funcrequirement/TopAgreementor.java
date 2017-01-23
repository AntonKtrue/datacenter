package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.transneft.TransneftEmployee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_topagreementor")
public class TopAgreementor extends Signatory
{

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    @NotNull
    private int sequence;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int order) {
        this.sequence = order;
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public TopAgreementor(TransneftEmployee employee, FuncRequirement funcRequirement, int sequence) {
        super(employee);
        this.funcRequirement = funcRequirement;
        this.sequence = sequence;
    }

    public TopAgreementor(FuncRequirement funcRequirement, int sequence) {
        this.funcRequirement = funcRequirement;
        this.sequence = sequence;
    }

    public TopAgreementor() {
    }

}
