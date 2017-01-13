package com.vaadin.demo.dashboard.model.transneft;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 13.01.2017.
 */
@Entity
@Table(name = "dict_employee")
public class TransneftEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String patroName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private TransneftDepartment department;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private TransneftRank rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    }
}
