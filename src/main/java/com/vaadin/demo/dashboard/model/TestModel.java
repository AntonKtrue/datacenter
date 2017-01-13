package com.vaadin.demo.dashboard.model;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

import com.vaadin.demo.dashboard.model.transneft.TransneftDepartment;
import com.vaadin.demo.dashboard.model.transneft.TransneftStructure;

import java.util.Collection;


/**
 * Created by Anton on 13.01.2017.
 */
public class TestModel {
    public static void main(String[] args) {
        System.out.println("test ");
        JPAContainer<TransneftStructure> structure = JPAContainerFactory.make(TransneftStructure.class, Config.JPA_UNIT);
        Collection<Object> listId = structure.getItemIds();
        for(Object o : listId) {
            System.out.println(
                    structure.getItem(o).getEntity().getName()

            );
            if(structure.getItem(o).getEntity().getDepartments().size()>0) {
                for(TransneftDepartment t : structure.getItem(o).getEntity().getDepartments()) {
                    System.out.println("\t" + t.getName());
                }
            }
        }
        System.exit(0);
    }
}
