package tn.kaz.ospas.model.funcrequirement;

import tn.kaz.ospas.model.Identity;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by Anton on 19.01.2017.
 */
public class Notice extends Identity {
    @ManyToOne
    private FuncRequirement funcRequirement;

    @OneToOne
    private NoticeType noticeType;

    public Notice() {
    }

    public FuncRequirement getFuncRequirement() {
        return funcRequirement;
    }

    public void setFuncRequirement(FuncRequirement funcRequirement) {
        this.funcRequirement = funcRequirement;
    }

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }
}
