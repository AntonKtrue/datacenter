package com.vaadin.demo.dashboard.model.staff;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Anton on 11.01.2017.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "dict_rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull @Size(min=2, max=200)
    private String name;
    @NotNull @Size(min=2, max=45)
    private String shortName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Rank(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Rank() {
        this("","");
    }

    @Override
    public String toString() {
        return name;
    }
}
