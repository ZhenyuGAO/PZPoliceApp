package com.pvirtech.pzpolice.ui.presenter;

import com.pvirtech.pzpolice.ui.contract.LeaveContract;

/**
 * Created by Administrator on 2017/02/28
 */

public class LeavePresenterImpl implements LeaveContract.Presenter {
    LeaveContract.Model model;
    LeaveContract.View view;

    public LeavePresenterImpl(LeaveContract.View view) {
        this.view = view;
    }


}