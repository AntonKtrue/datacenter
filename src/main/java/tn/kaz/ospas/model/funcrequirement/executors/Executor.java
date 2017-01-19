package tn.kaz.ospas.model.funcrequirement.executors;

import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.funcrequirement.WorkExecutors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Anton on 19.01.2017.
 */
@MappedSuperclass
public class Executor extends Identity {
    @OneToOne
    @PrimaryKeyJoinColumn
    private ExecutorType executorType;

    @ManyToOne
    private WorkExecutors workExecutors;

    public Executor() {

    }

    public WorkExecutors getWorkExecutors() {
        return workExecutors;
    }

    public void setWorkExecutors(WorkExecutors workExecutors) {
        this.workExecutors = workExecutors;
    }
}
