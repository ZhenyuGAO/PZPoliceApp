package com.pvirtech.pzpolice.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.third.view.DaKaView;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.contract.AttendanceContract;
import com.pvirtech.pzpolice.ui.presenter.AttendancePresenterImpl;

import butterknife.ButterKnife;

/**
 * 考勤打卡
 */
public class AttendanceActivity extends BaseActivity implements AttendanceContract.View {
    AttendanceContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ButterKnife.bind(this);
        initTitleView("考勤打卡");
        mContext = AttendanceActivity.this;
        TAG = "LeaveActivity";
        presenter = new AttendancePresenterImpl(AttendanceActivity.this, compositeSubscription);


        final DaKaView daKaView = (DaKaView) findViewById(R.id.daka_view);
        daKaView.init(mContext);
        daKaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daKaView.setSearching(true);
            }
        });


    }

    @Override
    public void initViewASignOut(String time, String address, String status) {

    }

    @Override
    public void initViewASign(String time, String address, String status) {

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

    @Override
    public void viewShowLoading(String msg) {

    }

    @Override
    public void viewHideLoading() {

    }

    @Override
    public void viewShowError(String msg) {

    }
}
