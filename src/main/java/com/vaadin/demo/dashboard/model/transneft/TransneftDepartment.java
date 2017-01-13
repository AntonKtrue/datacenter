package com.vaadin.demo.dashboard.model.transneft;

import com.vaadin.demo.dashboard.model.HasParent;
import com.vaadin.demo.dashboard.model.StandartEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Anton on 13.01.2017.
 */
@Entity
@Table(name = "dict_department")
public class TransneftDepartment extends StandartEntity implements HasParent<TransneftDepartment> {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TransneftDepartment parent;
    @OneToMany
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Set<TransneftDepartment> childs;

    @ManyToOne
    @JoinColumn(name = "structure_id")
    private TransneftStructure structure;

    @OneToMany
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Set<TransneftEmployee> employees;

    @Override
    public TransneftDepartment getParent() {
        return parent;
    }

    @Override
    public void setParent(TransneftDepartment parent) {
        this.parent = parent;
    }

    @Override
    public Set<TransneftDepartment> getChilds() {
        return childs;
    }

    @Override
    public void setChilds(Set<TransneftDepartment> childs) {
        this.childs = childs;
    }

    public TransneftStructure getStructure() {
        return structure;
    }

    public void setStructure(TransneftStructure structure) {
        this.structure = structure;
    }

    public Set<TransneftEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<TransneftEmployee> employees) {
        this.employees = employees;
    }


}
