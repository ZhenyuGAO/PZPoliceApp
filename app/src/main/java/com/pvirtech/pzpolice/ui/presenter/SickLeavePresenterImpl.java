package com.pvirtech.pzpolice.ui.presenter;

import com.pvirtech.pzpolice.http.HttpResult;
import com.pvirtech.pzpolice.http.RetrofitHttp;
import com.pvirtech.pzpolice.main.OperationUtils;
import com.pvirtech.pzpolice.main.one.CaseQueryEntity;
import com.pvirtech.pzpolice.ui.activity.SickLeaveActivity;
import com.pvirtech.pzpolice.ui.contract.SickLeaveContract;
import com.pvirtech.pzpolice.ui.model.SickLeaveModelImpl;
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
 * Created by Administrator on 2017/03/02
 */

public class SickLeavePresenterImpl implements SickLeaveContract.Presenter {
    SickLeaveContract.Model model = new SickLeaveModelImpl();
    SickLeaveContract.View view;
    CompositeSubscription compositeSubscription;

    public SickLeavePresenterImpl(SickLeaveActivity sickLeaveActivity, CompositeSubscription compositeSubscription) {
        this.compositeSubscription = compositeSubscription;
    }

    /**
     * 初始化销假界面数据
     */
    @Override
    public void initView() {
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
                model = new SickLeaveModelImpl("123456789", "病假", "2017-03-01 18:18", "2017-03-01 18:18", "想请假了", "");
                String totalTime = OperationUtils.getIntervalTime("2017-03-01 18:18", "2017-03-01 18:18");
                view.initView("123456789", "病假", "2017-03-01 18:18", "2017-03-01 18:18", "想请假了", totalTime);
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

    /**
     * 销假
     *
     * @param strId
     * @param strStartTime
     * @param strEndTime
     * @param strReason
     */
    @Override
    public void submit(String strId, String strStartTime, String strEndTime, String strReason) {
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