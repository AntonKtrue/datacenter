package tn.kaz.ospas.model.funcrequirement.executors;

import tn.kaz.ospas.model.Identity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_executor_type")
public class ExecutorType extends Identity {
    @NotNull
    private String name;

    public ExecutorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExecutorType() {
    }
}
