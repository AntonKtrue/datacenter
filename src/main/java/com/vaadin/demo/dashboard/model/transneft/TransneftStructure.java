package com.vaadin.demo.dashboard.model.transneft;

import com.vaadin.demo.dashboard.model.HasParent;
import com.vaadin.demo.dashboard.model.StandartEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Anton on 13.01.2017.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "dict_structure")
public class TransneftStructure extends StandartEntity implements HasParent<TransneftStructure> {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private StructureType type;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TransneftStructure parent;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Set<TransneftStructure> childs;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "structure_id", insertable = false, updatable = false)
    private Set<TransneftDepartment> departments;

    public StructureType getType() {
        return type;
    }

    public void setType(StructureType type) {
        this.type = type;
    }

    @Override
    public TransneftStructure getParent() {
        return parent;
    }

    @Override
    public void setParent(TransneftStructure transneftStructure) {
        this.parent = transneftStructure;
    }

    @Override
    public Set<TransneftStructure> getChilds() {
        return childs;
    }

    @Override
    public void setChilds(Set<TransneftStructure> t) {
        this.childs = t;
    }


    public Set<TransneftDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<TransneftDepartment> departments) {
        this.departments = departments;
    }

    public TransneftStructure(String name, String shortName, StructureType type, TransneftStructure parent, Set<TransneftStructure> childs, Set<TransneftDepartment> departments) {
        super(name, shortName);
        this.type = type;
        this.parent = parent;
        this.childs = childs;
        this.departments = departments;
    }

    public TransneftStructure(StructureType type, TransneftStructure parent, Set<TransneftStructure> childs, Set<TransneftDepartment> departments) {
        this.type = type;
        this.parent = parent;
        this.childs = childs;
        this.departments = departments;
    }

    public TransneftStructure() {
    }
}
