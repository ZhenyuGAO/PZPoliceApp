package com.pvirtech.pzpolice.entity;

/**
 * Created by youpengda on 2017/2/14.
 * 查看考勤情况的某一天的实体类
 */

public class WorkDay {
    /**
     * 日期的文本
     */
    private String text;
    /**
     * 1、空白天，2、普通天，3、今天，4、已选择
     */
    private int dayStatus;
    /**
     * 1、正常上班，2、请假，3,迟到早退，4、缺勤
     */
    private int workStatus;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDayStatus() {
        return dayStatus;
    }

    public void setDayStatus(int dayStatus) {
        this.dayStatus = dayStatus;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }
}
