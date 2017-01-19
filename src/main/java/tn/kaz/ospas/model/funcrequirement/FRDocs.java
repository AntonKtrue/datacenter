package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Anton on 19.01.2017.
 */
@Entity
@Table(name = "fr_docs")
public class FRDocs extends Identity {
    private String frFilePath;
    private String pmiFilePath;
    private String asiFilePath;
    private String psiFilePath;
    private String actFilePath;
    private String protFilePath;
    private String exchangeActFilePath;
    private String noticeFilePath;

    public FRDocs(String frFilePath, String pmiFilePath, String asiFilePath, String psiFilePath, String actFilePath, String protFilePath, String exchangeActFilePath, String noticeFilePath) {
        this.frFilePath = frFilePath;
        this.pmiFilePath = pmiFilePath;
        this.asiFilePath = asiFilePath;
        this.psiFilePath = psiFilePath;
        this.actFilePath = actFilePath;
        this.protFilePath = protFilePath;
        this.exchangeActFilePath = exchangeActFilePath;
        this.noticeFilePath = noticeFilePath;
    }

    public FRDocs() {
        this("","","","","","","","");
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
}
