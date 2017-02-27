package com.pvirtech.pzpolice.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.main.bottomnavigationbar.HomeMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

public class AppLoginActivity extends Activity {
    String tag = "AppLoginActivity";
    private Context mContext;
    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_login);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    @BindView(R.id.input_layout)
    LinearLayout input_layout;

    @BindView(R.id.layout_progress)
    LinearLayout layout_progress;

    @BindView(R.id.main_btn_login)
    Button main_btn_login;

    @BindView(R.id.edt_username)
    EditText edt_username;

    @BindView(R.id.input_element)
    LinearLayout input_element;

    @BindView(R.id.linear_username)
    LinearLayout linear_username;

    @BindView(R.id.linear_password)
    LinearLayout linear_password;

    @BindView(R.id.edt_password)
    EditText edt_password;

    private void initView() {
        edt_username.setText("dz");
        edt_password.setText("123456");
    }

    @OnClick(R.id.main_btn_login)
    void Login(View view) {

        String userName = edt_username.getText().toString();
        String passWord = edt_password.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(userName)) {
            edt_username.setError(getString(R.string.error_field_required));
            focusView = edt_username;
            cancel = true;
        }

        if (TextUtils.isEmpty(passWord)) {
            edt_password.setError(getString(R.string.error_field_required));
            focusView = edt_password;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            Login(userName, passWord);
        }
    }

    private void Login(String userName, String passWord) {
        layout_progress.setVisibility(View.VISIBLE);
        main_btn_login.setVisibility(View.GONE);
        Intent intent = new Intent(mContext, HomeMainActivity.class);
        startActivity(intent);
        finish();
    }


}
