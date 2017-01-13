//package com.vaadin.demo.dashboard.model.staff;
//
//import com.vaadin.demo.dashboard.model.TnObject;
//
//import javax.persistence.*;
//import java.util.*;
//
///**
// * Created by Anton on 11.01.2017.
// */
//@SuppressWarnings("serial")
//@Entity
//@Table(name = "dict_department")
//public class Department extends TnObject {
//
//    @Transient
//    private Boolean superDepartment;
//
//    @OneToMany
//    @JoinColumn(name = "parent_id")
//    private List<Department> children = new LinkedList<Department>();
//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name = "parent_id",insertable=false,updatable=false)
//    private Department parent;
//
////    @ManyToOne(optional = true)
////    @JoinColumn(name = "parent_id", referencedColumnName = "id")
////    private Department parent;
////
////    @OneToMany(
////
////            cascade = {CascadeType.ALL},
////            orphanRemoval = true
////    )
////    @JoinColumn(name = "parent_id")
////    private List<Department> children;
////
//    public List<Department> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Department> children) {
//        this.children = children;
//    }
//
//    public boolean isSuperDepartment() {
//        if (superDepartment == null) {
//            superDepartment = getChildren().size() > 0;
//        }
//        return superDepartment;
//    }
//
//    @Transient
//    public String getHierarchicalName() {
//        if (parent != null) {
//            return parent.toString() + " : " + name;
//        }
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        return getHierarchicalName();
//    }
//
//
//    public Department getParent() {
//        return parent;
//    }
//
//    public void setParent(Department parent) {
//        this.parent = parent;
//    }
//
//}
