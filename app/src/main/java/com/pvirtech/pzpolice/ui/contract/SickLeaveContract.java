package com.pvirtech.pzpolice.ui.contract;

/**
 * Created by youpengda on 2017/3/2.
 */

public interface SickLeaveContract {


    public interface View {

        void submitting();

        void submitSuccess();

        void submitFailed();

        void showWarning(String data);
    }

    public interface Presenter {

        void submit(String strId, String strStartTime, String strEndTime, String strReason);
    }

    public interface Model {

        String checkValidity();
    }


}