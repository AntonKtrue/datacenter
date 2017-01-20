package tn.kaz.ospas.model.funcrequirement;

import javax.persistence.*;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_topagreementor")
public class TopAgreementor extends Agreementor {

    @ManyToOne
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    private int order;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    @Override
    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public TopAgreementor() {

    }
}
