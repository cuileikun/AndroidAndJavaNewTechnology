package com.example.cuileikun.androidandjava.bean;

import java.io.Serializable;

/**
 * 报修提交手机号码返回信息
 * Created by Administrator on 2016/1/4.
 */
public class CustomerRomDetail implements Serializable {
    private String cutName;
    private String romAddress;
    private String createTime;
    private int romId;
    private int userId;
    private String mobile;
    private String declarant;
    private int memberId;
    private String callTelephone;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRomAddress() {
        return romAddress;
    }

    public void setRomAddress(String romAddress) {
        this.romAddress = romAddress;
    }

    public String getCutName() {
        return cutName;
    }

    public void setCutName(String cutName) {
        this.cutName = cutName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getRomId() {
        return romId;
    }

    public void setRomId(int romId) {
        this.romId = romId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getCallTelephone() {
        return callTelephone;
    }

    public void setCallTelephone(String callTelephone) {
        this.callTelephone = callTelephone;
    }
}
