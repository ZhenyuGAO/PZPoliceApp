package com.pvirtech.pzpolice.ui.contract;

/**
 * Created by youpengda on 2017/3/2.
 */

public interface BaseContractView {
    void submitting();

    void submitSuccess();

    void submitFailed();

    void showWarning(String data);
}
