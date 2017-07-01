package tn.kaz.ospas.model.transneft;

import tn.kaz.ospas.model.HasParent;
import tn.kaz.ospas.model.StandartEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Anton on 13.01.2017.
 */
@Entity
@Table(name = "dict_department")
public class TransneftDepartment extends StandartEntity implements HasParent<TransneftDepartment> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private TransneftDepartment parent;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Set<TransneftDepartment> childs;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransneftStructure structure;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department" ,fetch = FetchType.LAZY)
    private Set<TransneftEmployee> employees;

    @Transient
    private String caption;

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

    @Override
    public boolean childrenAllowed() {
        return childs.size() > 0;
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

    public TransneftDepartment(String name, String shortName, TransneftDepartment parent, TransneftStructure structure) {
        super(name, shortName);
        this.parent = parent;
        this.structure = structure;
    }

    public TransneftDepartment(TransneftDepartment parent, TransneftStructure structure) {
        this("","");
        this.parent = parent;
        this.structure = structure;
    }

    public TransneftDepartment(String name, String shortName) {
        super(name, shortName);
    }

    public TransneftDepartment() {
    }

    @Override
    public String toString() {
        return shortName;
    }

    public String getCaption() {
        if(caption == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(shortName);
            sb.append(" ");
            sb.append(structure.getShortName());
            caption = sb.toString();
        }
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
