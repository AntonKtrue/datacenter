package com.vaadin.demo.dashboard.model.staff;


import com.vaadin.demo.dashboard.model.TnObject;

import javax.persistence.*;

/**
 * Created by Anton on 11.01.2017.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "dict_rank")
public class Rank extends TnObject {

    public Rank(String name, String shortName) {
        super(name, shortName);
    }

    public Rank() {
        this("","");
    }
}
