package com.pvirtech.pzpolice.ui.model;

import com.pvirtech.pzpolice.ui.contract.SickLeaveContract;

/**
 * Created by Administrator on 2017/03/02
 */

public class SickLeaveModelImpl implements SickLeaveContract.Model {
    private String strId;
    private String strLeaveType;
    private String strLeaveStartTime;
    private String strLeaveEndTime;
    private String strLeaveReason;
    private String strLeaveTotalTime;

    public SickLeaveModelImpl() {
    }

    public SickLeaveModelImpl(String strId, String strLeaveType, String strLeaveStartTime, String strLeaveEndTime, String
            strLeaveReason, String strLeaveTotalTime) {
        this.strId = strId;
        this.strLeaveType = strLeaveType;
        this.strLeaveStartTime = strLeaveStartTime;
        this.strLeaveEndTime = strLeaveEndTime;
        this.strLeaveReason = strLeaveReason;
        this.strLeaveTotalTime = strLeaveTotalTime;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrLeaveType() {
        return strLeaveType;
    }

    public void setStrLeaveType(String strLeaveType) {
        this.strLeaveType = strLeaveType;
    }

    public String getStrLeaveStartTime() {
        return strLeaveStartTime;
    }

    public void setStrLeaveStartTime(String strLeaveStartTime) {
        this.strLeaveStartTime = strLeaveStartTime;
    }

    public String getStrLeaveEndTime() {
        return strLeaveEndTime;
    }

    public void setStrLeaveEndTime(String strLeaveEndTime) {
        this.strLeaveEndTime = strLeaveEndTime;
    }

    public String getStrLeaveReason() {
        return strLeaveReason;
    }

    public void setStrLeaveReason(String strLeaveReason) {
        this.strLeaveReason = strLeaveReason;
    }

    public String getStrLeaveTotalTime() {
        return strLeaveTotalTime;
    }

    public void setStrLeaveTotalTime(String strLeaveTotalTime) {
        this.strLeaveTotalTime = strLeaveTotalTime;
    }

    @Override
    public String checkValidity() {
        return null;
    }


}