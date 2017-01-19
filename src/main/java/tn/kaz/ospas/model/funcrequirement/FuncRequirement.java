package tn.kaz.ospas.model.funcrequirement;


import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.transneft.TransneftDepartment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_funcrequirement")
public class FuncRequirement extends Identity {

    @NotNull
    private long number;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private TransneftDepartment department;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Acceptor acceptor;

    @OneToMany
    private Set<TopAgreementor> topAgreementors;
    @OneToMany
    private Set<Agreementor> agreementors;

    @NotNull
    private Date date;

    @OneToMany
    private Set<FRCause> causes;

    @OneToMany
    private Set<Description> descriptions;

    @OneToOne
    private WorkExecutors executors;

    @OneToMany
    private Set<Notice> notice;

    @OneToOne
    private Limitation limitation;

    @OneToOne
    private FRDocs docs;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public TransneftDepartment getDepartment() {
        return department;
    }

    public void setDepartment(TransneftDepartment department) {
        this.department = department;
    }

    public Acceptor getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(Acceptor acceptor) {
        this.acceptor = acceptor;
    }

    public Set<TopAgreementor> getTopAgreementors() {
        return topAgreementors;
    }

    public void setTopAgreementors(Set<TopAgreementor> topAgreementors) {
        this.topAgreementors = topAgreementors;
    }

    public Set<Agreementor> getAgreementors() {
        return agreementors;
    }

    public void setAgreementors(Set<Agreementor> agreementors) {
        this.agreementors = agreementors;
    }

    public Date getFuncReqDate() {
        return date;
    }

    public void setFuncReqDate(Date funcReqDate) {
        this.date = funcReqDate;
    }

    public Set<FRCause> getCauses() {
        return causes;
    }

    public void setCauses(Set<FRCause> causes) {
        this.causes = causes;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public WorkExecutors getExecutors() {
        return executors;
    }

    public void setExecutors(WorkExecutors executors) {
        this.executors = executors;
    }

    public Set<Notice> getNoticeType() {
        return notice;
    }

    public void setNoticeType(Set<Notice> notice) {
        this.notice = notice;
    }

    public Limitation getLimitation() {
        return limitation;
    }

    public void setLimitation(Limitation limitation) {
        this.limitation = limitation;
    }

    public FRDocs getDocs() {
        return docs;
    }

    public void setDocs(FRDocs docs) {
        this.docs = docs;
    }
}
