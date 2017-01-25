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


    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public TopAgreementor(TransneftEmployee employee, FuncRequirement funcRequirement) {
        super(employee);
        this.funcRequirement = funcRequirement;

    }

    public TopAgreementor(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;

    }

    public TopAgreementor() {
    }

}
