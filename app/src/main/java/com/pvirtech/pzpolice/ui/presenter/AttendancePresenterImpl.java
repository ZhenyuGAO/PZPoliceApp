package com.pvirtech.pzpolice.ui.presenter;

import com.pvirtech.pzpolice.ui.contract.AttendanceContract;
import com.pvirtech.pzpolice.ui.model.AttendanceModelImpl;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/03/03
 */

public class AttendancePresenterImpl implements AttendanceContract.Presenter {
    AttendanceModelImpl model = new AttendanceModelImpl();
    AttendanceContract.View view;
    CompositeSubscription compositeSubscription;

    public AttendancePresenterImpl(AttendanceContract.View view, CompositeSubscription compositeSubscription) {
        this.view = view;
        this.compositeSubscription = compositeSubscription;
    }

    @Override
    public void initView() {

    }

    @Override
    public void submit(String latitude, String longitude, String addres, String Id, String time) {

    }
}