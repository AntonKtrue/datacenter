package tn.kaz.ospas.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Anton on 13.01.2017.
 */
@MappedSuperclass
public class StandartEntity extends Identity {


    @NotNull
    @Size(min=2, max=200)
    protected String name;

    @NotNull @Size(min=2, max=45)
    protected String shortName;

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

    public StandartEntity(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public StandartEntity() {
    }
}
