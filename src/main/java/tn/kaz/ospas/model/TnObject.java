package tn.kaz.ospas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by user on 11.01.17.
 */
@MappedSuperclass
public class TnObject {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotNull
    @Size(min=2, max=200)
    protected String name;

    @NotNull @Size(min=2, max=45)
    protected String shortName;

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

    @Override
    public String toString() {
        return name;
    }

    public TnObject(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public TnObject() {
        this("","");
    }
}
