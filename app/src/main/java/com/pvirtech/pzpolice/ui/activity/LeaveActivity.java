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
import com.pvirtech.pzpolice.ui.contract.LeaveContract;
import com.pvirtech.pzpolice.ui.presenter.LeavePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 请假主界面
 */
public class LeaveActivity extends BaseActivity implements LeaveContract.View {
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


    LeaveContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        ButterKnife.bind(this);
        initTitleView("请假");
        mContext = LeaveActivity.this;
        TAG = "LeaveActivity";
        presenter = new LeavePresenterImpl(LeaveActivity.this, compositeSubscription);
    }


    @Override
    public void selectedTotalTime(String data) {
        tvTotalTime.setText(data);
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
        System.out.println("aa");
        Toasty.warning(mContext, data).show();
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


    @OnClick({R.id.ll_leave_type, R.id.ll_statrt_time, R.id.ll_end_time, R.id.tv_total_time, R.id.ed_leave_reason, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_leave_type:
                presenter.showLeaveType(mContext, tvLeaveType.getText().toString(), new OnSelectedListener() {
                    @Override
                    public void onSelected(String data) {
                        tvLeaveType.setText(data);
                    }
                });
                break;
            case R.id.ll_statrt_time:
                DateAndTimePickerDialog dateAndTimePickerDialog = new DateAndTimePickerDialog();
                String strStartTime = tvStatrtTime.getText().toString();
                dateAndTimePickerDialog.show(LeaveActivity.this, strStartTime, "请选择时间", new OnSelectedListener() {
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
                dateAndTimePickerDialog02.show(LeaveActivity.this, strEndTime, "请选择时间", new OnSelectedListener() {
                    @Override
                    public void onSelected(String data) {
                        tvEndTime.setText(data);
                        tvTotalTime.setText(OperationUtils.getIntervalTime(tvStatrtTime.getText().toString(), tvEndTime.getText
                                ().toString()));
                    }
                });
                break;
            case R.id.tv_total_time:
                break;
            case R.id.ed_leave_reason:
                break;
            case R.id.submit:
                submit.setIndeterminateProgressMode(true);
                presenter.submit(clearText(tvLeaveType.getText().toString()), clearText(tvStatrtTime.getText().toString()),
                        clearText(tvEndTime.getText().toString()), clearText(edLeaveReason.getText().toString()));
                break;
        }
    }

    private String clearText(String data) {
        if (data.equals(mContext.getResources().getString(R.string.please_fill_in))) {
            return "";
        } else {
            return data;
        }
    }
}
