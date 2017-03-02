package com.pvirtech.pzpolice.ui.activity;

import android.os.Bundle;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.contract.SickLeaveContract;

import butterknife.ButterKnife;

/**
 * 销假主界面
 */
public class SickLeaveActivity extends BaseActivity implements SickLeaveContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sick_leave);
        ButterKnife.bind(this);
        initTitleView("销假");
        mContext = SickLeaveActivity.this;
        TAG = "LeaveActivity";

//        presenter = new LeavePresenterImpl(SickLeaveActivity.this, compositeSubscription);
    }

    @Override
    public void submitting() {

    }

    @Override
    public void submitSuccess() {

    }

    @Override
    public void submitFailed() {

    }

    @Override
    public void showWarning(String data) {

    }
}
