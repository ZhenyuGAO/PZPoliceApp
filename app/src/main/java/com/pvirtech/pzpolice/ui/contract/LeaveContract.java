package com.pvirtech.pzpolice.ui.contract;

import android.content.Context;

import com.pvirtech.pzpolice.ui.AppInterfaces.OnSelectedListener;

/**
 * Created by youpengda on 2017/2/28.
 */

public class LeaveContract {

    public interface View extends BaseContractView {
        void selectedTotalTime(String data);


    }

    public interface Presenter {
        void showLeaveType(Context mContext, String data, OnSelectedListener onSelectedListener);

        void submit(String strLeaveType, String strStartTime, String strEndTime, String strLeaveReason);
    }

    public interface Model {

        String checkValidity();
    }


}