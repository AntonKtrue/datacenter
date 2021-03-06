package tn.kaz.ospas.model.transneft;

import tn.kaz.ospas.model.HasParent;
import tn.kaz.ospas.model.StandartEntity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private TransneftStructure parent;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Set<TransneftStructure> childs;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "structure_id")
    private Set<TransneftDepartment> departments;

    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    @Transient
    public TransneftStructure getParentCodedId() {
        TransneftStructure upParent = parent;

        while(upParent.getType() !=
                StructureType.OST
                && upParent.getType() != StructureType.UMN
                && upParent.getType() != StructureType.OBJ ) {
            upParent = upParent.getParent();
        }
        return upParent;
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

    @Override
    public boolean childrenAllowed() {
        return childs.size() > 0;
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
    public TransneftStructure(StructureType type, TransneftStructure parent) {
        super("","");
        this.code = "";
        int ord = type.ordinal();
        int ordCount = StructureType.values().length;

        if((ord+1) >= ordCount) {
            this.type = type;
            this.parent = parent.getParent();
        } else {
            ord++;
            this.type = StructureType.getTypeByOrdinal(ord);
            this.parent = parent;
        }
    }

    public TransneftStructure(TransneftStructure selectedStructure) {
        super("","");
        this.code = "";
        int ord = selectedStructure.getType().ordinal();
        int ordCount = StructureType.values().length;

        if((ord+1) >= ordCount) {
            this.type = selectedStructure.getType();
            this.parent = selectedStructure.getParent();
        } else {
            ord++;
            this.type = StructureType.getTypeByOrdinal(ord);
            this.parent = selectedStructure;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
