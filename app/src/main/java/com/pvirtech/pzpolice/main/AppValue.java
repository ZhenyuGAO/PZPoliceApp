package com.pvirtech.pzpolice.main;


import com.pvirtech.pzpolice.entity.UserInfo;

/**
 * Created by pd on 2016/10/10.
 * 存放APP会用到的一些常量
 */

public class AppValue {
    private static AppValue instance = null;

    /**
     * 数据库地址
     */
    private String dbPath = null;
    /**
     * 图片地址
     */
    private String imgPath = null;
    /***
     * 系统更新路径
     */
    private String updatePath = null;
    /**
     * 用户信息
     */
    private UserInfo mUserInfo;
    /**
     * 设备ID
     */
    private String strDeviceId;

    public static AppValue getInstance() {
        if (instance == null) {
            instance = new AppValue();
        }
        return instance;
    }

    public UserInfo getmUserInfo() {
        return mUserInfo;
    }

    public void setmUserInfo(UserInfo mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    public String getStrDeviceId() {
        return strDeviceId;
    }

    public void setStrDeviceId(String strDeviceId) {
        this.strDeviceId = strDeviceId;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getUpdatePath() {
        return updatePath;
    }

    public void setUpdatePath(String updatePath) {
        this.updatePath = updatePath;
    }

}
