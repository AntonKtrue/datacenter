package com.vaadin.demo.dashboard.model.staff;

import com.vaadin.demo.dashboard.model.TnObject;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Created by Anton on 11.01.2017.
 */

public class Department extends TnObject {

    @ManyToOne
    private Department parent;
    @OneToMany(mappedBy = "department")
    private Set<Person> person;
    @OneToOne(mappedBy = "id")
    private Person head;



    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public Set<Person> getPerson() {
        return person;
    }

    public void setPerson(Set<Person> person) {
        this.person = person;
    }

    public Person getHead() {
        return head;
    }

    public void setHead(Person head) {
        this.head = head;
    }
}
