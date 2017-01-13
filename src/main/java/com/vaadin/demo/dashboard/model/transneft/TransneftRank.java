package com.vaadin.demo.dashboard.model.transneft;


import com.vaadin.demo.dashboard.model.StandartEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anton on 11.01.2017.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "dict_rank")
public class TransneftRank extends StandartEntity {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private List<TransneftEmployee> employees;

    public TransneftRank(String name, String shortName) {
        super(name, shortName);
    }

    public TransneftRank() {
        this("","");
    }
}
