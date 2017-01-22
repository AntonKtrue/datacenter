package tn.kaz.ospas.model.funcrequirement;


import tn.kaz.ospas.model.Identity;
import tn.kaz.ospas.model.transneft.TransneftDepartment;
import tn.kaz.ospas.model.transneft.TransneftStructure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(max = 255)
    private String shortDescription;


    @ManyToOne
    @JoinColumn(name = "structure_id")
    private TransneftStructure structure;



    @OneToOne
    @PrimaryKeyJoinColumn
    private Acceptor acceptor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcRequirement")
    private Set<TopAgreementor> topAgreementors;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcRequirement")
    private Set<Agreementor> agreementors;

    @NotNull
    private Date date;

    @OneToMany
    private Set<FRCause> causes;

    @OneToMany
    private Set<Description> descriptions;

    @OneToOne
    private WorkExecutors executors;

    private Date developmentDate;

    private Date standTestDate;

    private Date implementationDate;

    private String frFilePath;

    private String pmiFilePath;

    private String asiFilePath;

    private String psiFilePath;

    private String actFilePath;

    private String protFilePath;

    private String exchangeActFilePath;

    private String noticeFilePath;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDevelopmentDate() {
        return developmentDate;
    }

    public void setDevelopmentDate(Date developmentDate) {
        this.developmentDate = developmentDate;
    }

    public Date getStandTestDate() {
        return standTestDate;
    }

    public void setStandTestDate(Date standTestDate) {
        this.standTestDate = standTestDate;
    }

    public Date getImplementationDate() {
        return implementationDate;
    }

    public void setImplementationDate(Date implementationDate) {
        this.implementationDate = implementationDate;
    }

    public String getFrFilePath() {
        return frFilePath;
    }

    public void setFrFilePath(String frFilePath) {
        this.frFilePath = frFilePath;
    }

    public String getPmiFilePath() {
        return pmiFilePath;
    }

    public void setPmiFilePath(String pmiFilePath) {
        this.pmiFilePath = pmiFilePath;
    }

    public String getAsiFilePath() {
        return asiFilePath;
    }

    public void setAsiFilePath(String asiFilePath) {
        this.asiFilePath = asiFilePath;
    }

    public String getPsiFilePath() {
        return psiFilePath;
    }

    public void setPsiFilePath(String psiFilePath) {
        this.psiFilePath = psiFilePath;
    }

    public String getActFilePath() {
        return actFilePath;
    }

    public void setActFilePath(String actFilePath) {
        this.actFilePath = actFilePath;
    }

    public String getProtFilePath() {
        return protFilePath;
    }

    public void setProtFilePath(String protFilePath) {
        this.protFilePath = protFilePath;
    }

    public String getExchangeActFilePath() {
        return exchangeActFilePath;
    }

    public void setExchangeActFilePath(String exchangeActFilePath) {
        this.exchangeActFilePath = exchangeActFilePath;
    }

    public String getNoticeFilePath() {
        return noticeFilePath;
    }

    public void setNoticeFilePath(String noticeFilePath) {
        this.noticeFilePath = noticeFilePath;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public TransneftStructure getStructure() {
        return structure;
    }

    public void setStructure(TransneftStructure structure) {
        this.structure = structure;
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

    public FuncRequirement(TransneftStructure structure) {
        this.structure = structure;
    }

    public FuncRequirement() {
        shortDescription ="";
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }




}
