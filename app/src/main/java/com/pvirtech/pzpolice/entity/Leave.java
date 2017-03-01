package com.pvirtech.pzpolice.entity;

/**
 * Created by youpengda on 2017/3/1.
 */

public class Leave {
    private String strType;
    private int intType;

    public Leave(String strType, int intType) {
        this.strType = strType;
        this.intType = intType;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public int getIntType() {
        return intType;
    }

    public void setIntType(int intType) {
        this.intType = intType;
    }
}
