package com.pvirtech.pzpolice.main;

import java.text.SimpleDateFormat;

/**
 * Created by youpengda on 2017/3/2.
 */

public class OperationUtils {
    public static String getIntervalTime(String dateOne, String dateTwo) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long between = 0;
        try {
            java.util.Date begin = dfs.parse(dateOne);
            java.util.Date end = dfs.parse(dateTwo);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        if (between < 0) {
            return "";
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
//        System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms + "毫秒");
        String data = day + "天" + hour + "小时" + min + "分";
        return data;
    }

    public static String compareTime(String dateOne, String dateTwo) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long between = 0;
        try {
            java.util.Date begin = dfs.parse(dateOne);
            java.util.Date end = dfs.parse(dateTwo);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
            return "请选择时间";
        }
        if (between > 0) {
            return Constant.SUCCESS;
        } else {
            return "开始时间大于结束时间，请重新选择时间";
        }
    }
}
