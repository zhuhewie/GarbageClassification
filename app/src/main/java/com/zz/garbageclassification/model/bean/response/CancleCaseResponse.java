package com.zz.garbageclassification.model.bean.response;

public class CancleCaseResponse {
    private String displayNo;           //案号
    private String assignedManTelPhone; //电话
    private String noticeMessage;       //详情
    private String assignedMan;         //秘书

    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    public String getAssignedManTelPhone() {
        return assignedManTelPhone;
    }

    public void setAssignedManTelPhone(String assignedManTelPhone) {
        this.assignedManTelPhone = assignedManTelPhone;
    }

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public String getAssignedMan() {
        return assignedMan;
    }

    public void setAssignedMan(String assignedMan) {
        this.assignedMan = assignedMan;
    }
}
