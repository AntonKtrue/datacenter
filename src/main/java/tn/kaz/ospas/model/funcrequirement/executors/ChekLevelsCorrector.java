package tn.kaz.ospas.model.funcrequirement.executors;

import tn.kaz.ospas.model.funcrequirement.WorkExecutors;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_checklevels_corrector")
public class ChekLevelsCorrector extends Executor {

}
