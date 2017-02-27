package com.pvirtech.pzpolice.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseActivity;

public class SettingActivity extends BaseActivity {
    private Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mContext=this;
        initTitleView(mContext.getResources().getString(R.string.setting));
    }
}
