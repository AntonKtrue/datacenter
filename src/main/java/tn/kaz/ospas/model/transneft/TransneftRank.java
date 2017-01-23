package tn.kaz.ospas.model.transneft;


import tn.kaz.ospas.model.StandartEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anton on 11.01.2017.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "dict_rank")
public class TransneftRank extends StandartEntity {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id", insertable = false, updatable = false)
    private List<TransneftEmployee> employees;

    public TransneftRank(String name, String shortName) {
        super(name, shortName);
    }

    public TransneftRank() {
        this("","");
    }

    @Override
    public String toString() {
        return shortName;
    }
}
