package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_limitation")
public class Limitation extends Identity {
    private Date development;
    private Date standTest;
    private Date implementation;
    @OneToOne
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    public Limitation(Date development, Date standTest, Date implementation, FuncRequirement funcRequirement) {
        this.development = development;
        this.standTest = standTest;
        this.implementation = implementation;
        this.funcRequirement = funcRequirement;
    }

    public Limitation() {
    }

    public Date getDevelopment() {
        return development;
    }

    public void setDevelopment(Date development) {
        this.development = development;
    }

    public Date getStandTest() {
        return standTest;
    }

    public void setStandTest(Date standTest) {
        this.standTest = standTest;
    }

    public Date getImplementation() {
        return implementation;
    }

    public void setImplementation(Date implementation) {
        this.implementation = implementation;
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }
}
