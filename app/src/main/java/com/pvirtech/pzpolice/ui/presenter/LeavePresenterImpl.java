package com.pvirtech.pzpolice.ui.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.entity.Leave;
import com.pvirtech.pzpolice.enumeration.LeaveType;
import com.pvirtech.pzpolice.http.HttpResult;
import com.pvirtech.pzpolice.http.RetrofitHttp;
import com.pvirtech.pzpolice.main.Constant;
import com.pvirtech.pzpolice.main.OperationUtils;
import com.pvirtech.pzpolice.main.one.CaseQueryEntity;
import com.pvirtech.pzpolice.ui.AppInterfaces.OnSelectedListener;
import com.pvirtech.pzpolice.ui.contract.LeaveContract;
import com.pvirtech.pzpolice.ui.model.LeaveModelImpl;
import com.pvirtech.pzpolice.utils.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/02/28
 */

public class LeavePresenterImpl implements LeaveContract.Presenter {
    LeaveModelImpl model = new LeaveModelImpl();
    LeaveContract.View view;
    CompositeSubscription compositeSubscription;

    public LeavePresenterImpl(LeaveContract.View view, CompositeSubscription compositeSubscription) {
        this.view = view;
        this.compositeSubscription = compositeSubscription;
    }


    @Override
    public void showLeaveType(Context mContext, String data, final OnSelectedListener onSelectedListener) {
        int length = LeaveType.values().length;
        final int items[] = new int[length];
        final String item[] = new String[length];
        int i = 0;
        int selectedPostion = 0;
//        String data = inputView.getText().toString();
        for (LeaveType leaveType : LeaveType.values()) {
            Leave leave = leaveType.getValue();
            item[i] = leave.getStrType();
            items[i] = leave.getIntType();
            i++;
            if (data.equals(leave.getStrType())) {
                selectedPostion = i;
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  //先得到构造器
        View mTitleView = LayoutInflater.from(mContext).inflate(R.layout.dialog_title, null);
        TextView tvTitle = (TextView) mTitleView.findViewById(R.id.tv_title);
        tvTitle.setText("请选择请假类型");
        ImageView iv_Title = (ImageView) mTitleView.findViewById(R.id.iv_title);
        iv_Title.setImageResource(R.mipmap.type);
        builder.setCustomTitle(mTitleView);

        builder.setSingleChoiceItems(item, selectedPostion - 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                inputView.setText(item[which]);
                for (LeaveType leaveType : LeaveType.values()) {
                    Leave leave = leaveType.getValue();
                    if (item[which].equals(leave.getStrType())) {
                        onSelectedListener.onSelected(String.valueOf(leave.getStrType()));
                    }
                }
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


    /*@Override
    public void showTotalTime() {
        if (!TextUtils.isEmpty(model.getStrLeaveStartTime()) && !TextUtils.isEmpty(model.getStrLeaveEndTime())) {
            if (!compareTime(model.getStrLeaveStartTime(), model.getStrLeaveEndTime()).equals(Constant.SUCCESS)) {
                return;
            }
            model.setStrLeaveTotalTime(getIntervalTime(model.getStrLeaveStartTime(), model.getStrLeaveEndTime()));
            view.selectedTotalTime(model.getStrLeaveTotalTime());
        }
    }*/

    @Override
    public void submit(String strLeaveType, String strStartTime, String strEndTime, String strLeaveReason) {
        model = new LeaveModelImpl(strLeaveType, strStartTime, strEndTime, strLeaveReason, null);

        if (!Constant.SUCCESS.equals(model.checkValidity())) {
            view.showWarning(model.checkValidity());
            return;
        }

        String checkResult = OperationUtils.compareTime(strStartTime, strEndTime);
        if (!Constant.SUCCESS.equals(checkResult)) {
            view.showWarning(checkResult);
            return;
        }


        Map map = new HashMap();
        Subscription subscribe01 = RetrofitHttp.provideClientApi().CaseQueryData(map).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                int a = 0;
                L.d("sucess");
                view.submitting();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<HttpResult<List<CaseQueryEntity>>>() {
            @Override
            public void call(HttpResult<List<CaseQueryEntity>> response) {
                String s = response.getResultCode();
                response.getData();
                L.d("sucess");
                view.submitSuccess();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                L.d("sucess");
                view.submitFailed();
            }
        });
        compositeSubscription.add(subscribe01);
    }
}