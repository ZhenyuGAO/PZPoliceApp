package com.pvirtech.pzpolice.main;


import com.pvirtech.pzpolice.entity.UserInfo;

/**
 * Created by pd on 2016/10/10.
 */

public class AppValue {
    private static AppValue instance = null;


    private  String dbPath = null;
    private  String imgPath = null;
    private  String updatePath = null;
    private UserInfo mUserInfo;

    public static AppValue getInstance() {
        if (instance == null) {
            instance = new AppValue();
        }
        return instance;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }
    public  String getDbPath() {
        return dbPath;
    }

    public  void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public  String getImgPath() {
        return imgPath;
    }

    public  void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public  String getUpdatePath() {
        return updatePath;
    }

    public  void setUpdatePath(String updatePath) {
        this.updatePath = updatePath;
    }

}
