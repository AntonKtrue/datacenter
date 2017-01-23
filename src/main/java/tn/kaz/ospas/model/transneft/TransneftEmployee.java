package tn.kaz.ospas.model.transneft;

import tn.kaz.ospas.model.Identity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by Anton on 13.01.2017.
 */
@Entity
@Table(name = "dict_employee")
public class TransneftEmployee extends Identity {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 40, message = "name must be longer than 3 and less than 40 characters")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 45)
    private String lastName;

    private String patroName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private TransneftDepartment department;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private TransneftRank rank;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatroName() {
        return patroName;
    }

    public void setPatroName(String patroName) {
        this.patroName = patroName;
    }

    public TransneftDepartment getDepartment() {
        return department;
    }

    public void setDepartment(TransneftDepartment department) {
        this.department = department;
    }

    public TransneftRank getRank() {
        return rank;
    }

    public void setRank(TransneftRank rank) {
        this.rank = rank;
    }

    public TransneftEmployee(String firstName, String lastName, String patroName, TransneftDepartment department, TransneftRank rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patroName = patroName;
        this.department = department;
        this.rank = rank;
    }

    public TransneftEmployee() {
//        this.firstName = "";
//        this.lastName = "";
//        this.patroName = "";
    }

    @Override
    public String toString() {
        return lastName + " " + firstName.substring(0,1) + "." + (patroName!=null ? patroName.substring(0,1) + "." : "" );
    }

    public TransneftEmployee(TransneftDepartment department) {
        this();
        this.department = department;
    }
}
