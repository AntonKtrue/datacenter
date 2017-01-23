package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftEmployee;
import tn.kaz.ospas.model.transneft.TransneftRank;

import javax.persistence.*;

/**
 * Created by Anton on 19.01.2017.
 */
@MappedSuperclass
public class Signatory extends Identity {
    @ManyToOne(fetch = FetchType.LAZY)
    private TransneftEmployee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    private TransneftRank rank;
    @ManyToOne(fetch = FetchType.LAZY)
    private TransneftDepartment department;

    public Signatory(TransneftEmployee employee) {
        this.employee = employee;
        this.rank = employee.getRank();
        this.department = employee.getDepartment();
    }

    public Signatory() {
    }

    public TransneftEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(TransneftEmployee employee) {
        this.employee = employee;
    }

    public TransneftRank getRank() {
        return rank;
    }

    public void setRank(TransneftRank rank) {
        this.rank = rank;
    }

    public TransneftDepartment getDepartment() {
        return department;
    }

    public void setDepartment(TransneftDepartment department) {
        this.department = department;
    }
}
