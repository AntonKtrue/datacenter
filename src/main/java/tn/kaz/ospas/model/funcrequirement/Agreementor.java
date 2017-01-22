package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.transneft.TransneftEmployee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_agreementor")
public class Agreementor extends Signatory {
    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    @NotNull
    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public Agreementor(TransneftEmployee employee, FuncRequirement funcRequirement, int order) {
        super(employee);
        this.funcRequirement = funcRequirement;
        this.order = order;
    }

    public Agreementor(FuncRequirement funcRequirement, int order) {
        this.funcRequirement = funcRequirement;
        this.order = order;
    }

    public Agreementor() {
    }
}
