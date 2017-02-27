package com.pvirtech.pzpolice.main;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.pvirtech.pzpolice.third.baidu.LocationService;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by pd on 2016/8/25.
 */
public class MyApplication extends android.app.Application {
    private static MyApplication instance = null;
    /***
     * 初始化定位sdk，建议在Application中创建
     */
    public LocationService locationService;
    public Vibrator mVibrator;

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;

    }

    private RefWatcher refWatcher;

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public static RefWatcher getReWatcher(Context context) {
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        return myApplication.getRefWatcher();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        /*程序崩溃生成日志*/
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(this);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }

}
