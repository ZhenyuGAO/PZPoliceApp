package com.pvirtech.pzpolice.enumeration;

/**
 * Created by youpengda on 2017/2/14.
 * 这一天的工作状态
 */

public enum WorkStatus {
    /**
     * 空
     */
    EMPTY(1),
    /**
     *正常
     */
    MORMAL(2),
    /**
     * 请假
     */
    LEAVE(3),
    /**
     * 迟到早退
     */
    TARDINESS(4),
    /**
     * 缺卡
     */
    LACK(5);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    WorkStatus(int value) {

        this.value = value;
    }
}
