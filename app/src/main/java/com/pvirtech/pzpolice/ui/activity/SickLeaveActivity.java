package com.pvirtech.pzpolice.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.main.OperationUtils;
import com.pvirtech.pzpolice.third.DateAndTimePickerDialog;
import com.pvirtech.pzpolice.ui.appInterfaces.OnSelectedListener;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.contract.SickLeaveContract;
import com.pvirtech.pzpolice.ui.presenter.SickLeavePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 销假主界面
 */
public class SickLeaveActivity extends BaseActivity implements SickLeaveContract.View {

    @BindView(R.id.tv_leave_type)
    TextView tvLeaveType;
    @BindView(R.id.tv_statrt_time)
    TextView tvStatrtTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;
    @BindView(R.id.ed_leave_reason)
    EditText edLeaveReason;
    @BindView(R.id.submit)
    CircularProgressButton submit;
    @BindView(R.id.ed_sick_reason)
    EditText edSickReason;
    SickLeaveContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sick_leave);
        ButterKnife.bind(this);
        initTitleView("销假");
        mContext = SickLeaveActivity.this;
        TAG = "LeaveActivity";
        presenter = new SickLeavePresenterImpl(SickLeaveActivity.this, compositeSubscription);
        presenter.initView();
    }


    @Override
    public void initView(String strId, String strLeaveType, String strLeaveStartTime, String strLeaveEndTime, String
            strLeaveReason, String strLeaveTotalTime) {
        tvLeaveType.setText(strLeaveType);
        tvStatrtTime.setText(strLeaveStartTime);
        tvEndTime.setText(strLeaveEndTime);
        edLeaveReason.setText(strLeaveReason);
        tvTotalTime.setText(strLeaveTotalTime);
    }

    @Override
    public void submitting() {
        submit.setProgress(50);
    }

    @Override
    public void submitSuccess() {
        Toasty.success(mContext, "提交成功").show();
        submit.setProgress(100);

    }

    @Override
    public void submitFailed() {
        Toasty.error(mContext, "提交失败，请重新提交").show();
        submit.setProgress(0);
    }

    @Override
    public void showWarning(String data) {
        Toasty.warning(mContext, data).show();
    }

    @Override
    public void viewShowLoading(String msg) {
        showLoading(msg);
    }

    @Override
    public void viewHideLoading() {
        hideLoading();
    }

    @Override
    public void viewShowError(String msg) {
        showError(msg);
    }


    @OnClick({R.id.submit, R.id.ll_statrt_time, R.id.ll_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                presenter.submit("id123456789", tvStatrtTime.getText().toString(), tvEndTime.getText().toString(), edSickReason
                        .getText().toString());
                break;
            case R.id.ll_statrt_time:
                DateAndTimePickerDialog dateAndTimePickerDialog = new DateAndTimePickerDialog();
                String strStartTime = tvStatrtTime.getText().toString();
                dateAndTimePickerDialog.show(SickLeaveActivity.this, strStartTime, "请选择时间", new OnSelectedListener() {
                    @Override
                    public void onSelected(String data) {
                        tvStatrtTime.setText(data);
                        tvTotalTime.setText(OperationUtils.getIntervalTime(tvStatrtTime.getText().toString(), tvEndTime.getText
                                ().toString()));
                    }
                });
                break;
            case R.id.ll_end_time:
                DateAndTimePickerDialog dateAndTimePickerDialog02 = new DateAndTimePickerDialog();
                String strEndTime = tvEndTime.getText().toString();
                dateAndTimePickerDialog02.show(SickLeaveActivity.this, strEndTime, "请选择时间", new OnSelectedListener() {
                    @Override
                    public void onSelected(String data) {
                        tvEndTime.setText(data);
                        tvTotalTime.setText(OperationUtils.getIntervalTime(tvStatrtTime.getText().toString(), tvEndTime.getText
                                ().toString()));
                    }
                });
                break;
        }
    }


}
