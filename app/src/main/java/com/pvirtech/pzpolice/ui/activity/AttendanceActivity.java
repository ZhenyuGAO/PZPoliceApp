package com.pvirtech.pzpolice.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.third.view.DaKaView;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.base.BaseDialog;
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


        DaKaView daKaView = (DaKaView) findViewById(R.id.daka_view);
        daKaView.setSearching(true);
        final ImageView view = new ImageView(mContext);
        view.setImageResource(R.mipmap.ic_launcher);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("aaaaa");
            }
        });
        daKaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDialog baseDialog = new BaseDialog(mContext, R.style.loading_dialog_style);
                baseDialog.setPositiviOnclickListener(new BaseDialog.OnBaseDialogClickListener() {
                    @Override
                    public void onClick(BaseDialog baseDialog) {
                        System.out.println("aaaaa");
                    }
                });
                baseDialog.setNegativeOnclickListener(new BaseDialog.OnBaseDialogClickListener() {
                    @Override
                    public void onClick(BaseDialog baseDialog) {
                    }
                });
                baseDialog.setMyContentView(view);
                baseDialog.show();
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
