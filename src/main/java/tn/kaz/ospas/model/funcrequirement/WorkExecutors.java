package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.funcrequirement.executors.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_workexecutors")
public class WorkExecutors extends Identity {

    @OneToOne
    @PrimaryKeyJoinColumn
    private FuncRequirement funcRequirement;

    @OneToMany
    private Set<MpsaCorrector> mpsaCorrectors ;

    @OneToMany
    private Set<SdkuCorrector> sdkuCorrectors;

    @OneToMany
    private Set<KdCorrector> kdCorrectors;

    @OneToMany
    private Set<NewTechDocsExecutor> docsExecutors;

    @OneToMany
    private Set<ChekLevelsCorrector> chekLevelsCorrectors;

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public Set<MpsaCorrector> getMpsaCorrectors() {
        return mpsaCorrectors;
    }

    public void setMpsaCorrectors(Set<MpsaCorrector> mpsaCorrectors) {
        this.mpsaCorrectors = mpsaCorrectors;
    }

    public Set<SdkuCorrector> getSdkuCorrectors() {
        return sdkuCorrectors;
    }

    public void setSdkuCorrectors(Set<SdkuCorrector> sdkuCorrectors) {
        this.sdkuCorrectors = sdkuCorrectors;
    }

    public Set<KdCorrector> getKdCorrectors() {
        return kdCorrectors;
    }

    public void setKdCorrectors(Set<KdCorrector> kdCorrectors) {
        this.kdCorrectors = kdCorrectors;
    }

    public Set<NewTechDocsExecutor> getDocsExecutors() {
        return docsExecutors;
    }

    public void setDocsExecutors(Set<NewTechDocsExecutor> docsExecutors) {
        this.docsExecutors = docsExecutors;
    }

    public Set<ChekLevelsCorrector> getChekLevelsCorrectors() {
        return chekLevelsCorrectors;
    }

    public void setChekLevelsCorrectors(Set<ChekLevelsCorrector> chekLevelsCorrectors) {
        this.chekLevelsCorrectors = chekLevelsCorrectors;
    }
}
