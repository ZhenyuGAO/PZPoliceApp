package com.pvirtech.pzpolice.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.utils.AppManager;
import com.pvirtech.pzpolice.utils.LoadingViewProgress;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pd on 2016/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    public CompositeSubscription compositeSubscription = new CompositeSubscription();
    public Context mContext;
    public String TAG;
    LoadingViewProgress loadingViewProgress = new LoadingViewProgress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        loadingViewProgress.hideDialogForLoading();//防止内存泄漏
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    public void initTitleView(String title) {
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);//设置主标题
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 4.0以上Navigation默认false
            actionBar.setDisplayHomeAsUpEnabled(true);
            // Title默认true
            actionBar.setDisplayShowTitleEnabled(true);
            // Logo默认true
            actionBar.setDisplayUseLogoEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(String msg) {
        if (TextUtils.isEmpty(msg)) {
            loadingViewProgress.showDialogForLoading(this, "正在加载。。。", false);
        } else {
            loadingViewProgress.showDialogForLoading(this, msg, false);
        }
    }

    @Override
    public void hideLoading() {
        loadingViewProgress.hideDialogForLoading();
    }

    @Override
    public void showError(String msg) {
        loadingViewProgress.hideDialogForLoading();
    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            loadingViewProgress.hideDialogForLoading();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
