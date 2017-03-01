package com.pvirtech.pzpolice.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.entity.Leave;
import com.pvirtech.pzpolice.enumeration.LeaveType;
import com.pvirtech.pzpolice.third.DateAndTimePickerDialog;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.contract.LeaveContract;
import com.pvirtech.pzpolice.ui.presenter.LeavePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

    private Context mContext;
    private String TAG = "LeaveActivity";
    LeaveContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        ButterKnife.bind(this);
        initTitleView("请假");
        mContext = LeaveActivity.this;
        presenter = new LeavePresenterImpl(LeaveActivity.this);
    }

    private void show(Context mContext) {
        int length = LeaveType.values().length;
        final int items[] = new int[length];
        String item[] = new String[length];
        int i = 0;
        for (LeaveType leaveType : LeaveType.values()) {
            Leave leave = leaveType.getValue();
            item[i] = leave.getStrType();
            items[i] = leave.getIntType();
            i++;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  //先得到构造器
        builder.setTitle("请选择请假类型"); //设置标题
        View mTitleView = LayoutInflater.from(mContext).inflate(R.layout.dialog_title, null);
        builder.setCustomTitle(mTitleView);
        builder.setIcon(R.mipmap.type);//设置图标，图片id即可
        builder.setSingleChoiceItems(item, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void selectedStartTime(String data) {

    }

    @OnClick({R.id.tv_leave_type, R.id.tv_statrt_time, R.id.tv_end_time, R.id.tv_total_time, R.id.ed_leave_reason, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_leave_type:
                show(mContext);
                break;
            case R.id.tv_statrt_time:
                DateAndTimePickerDialog dateAndTimePickerDialog = new DateAndTimePickerDialog();
                dateAndTimePickerDialog.show(LeaveActivity.this, tvStatrtTime, "请选择时间");
                break;
            case R.id.tv_end_time:
                DateAndTimePickerDialog dateAndTimePickerDialog02 = new DateAndTimePickerDialog();
                dateAndTimePickerDialog02.show(LeaveActivity.this, tvEndTime, "请选择时间");
                break;
            case R.id.tv_total_time:
                break;
            case R.id.ed_leave_reason:
                break;
            case R.id.submit:
                submit.setIndeterminateProgressMode(true);
                if (submit.getProgress() == 0) {
                    submit.setProgress(50);
                } else if (submit.getProgress() == 100) {
                    submit.setProgress(0);
                } else {
                    submit.setProgress(100);
                }
                submit.setProgress(-1);
                break;
        }
    }
}
