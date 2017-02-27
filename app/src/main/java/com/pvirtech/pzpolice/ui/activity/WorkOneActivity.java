package com.pvirtech.pzpolice.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.ui.contract.WorkOneContract;
import com.pvirtech.pzpolice.ui.presenter.WorkOnePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkOneActivity extends BaseActivity implements WorkOneContract.View {
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.button3)
    Button button3;
    WorkOneContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_one);
        ButterKnife.bind(this);
        initTitleView("title");
        presenter = new WorkOnePresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void getDataSucessed(String data) {
        hideLoading();
        tvContent.setText(data);
    }

    @OnClick(R.id.button3)
    public void onClick() {
        showLoading("");
        presenter.getData();
    }

    @Override
    public void getDataFailed(String data) {
        showError("");
    }
}
