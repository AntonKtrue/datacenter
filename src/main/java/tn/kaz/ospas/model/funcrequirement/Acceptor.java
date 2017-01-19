package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftRank;

import javax.persistence.*;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_acceptor")
public class Acceptor extends Signatory {
    @ManyToOne
    @JoinColumn(name = "funcrequirement_id")
    private FuncRequirement funcRequirement;

    public Acceptor(TransneftEmployee employee, FuncRequirement funcRequirement) {
        super(employee);
        this.funcRequirement = funcRequirement;
    }

    public Acceptor(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public Acceptor() {
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }
}
