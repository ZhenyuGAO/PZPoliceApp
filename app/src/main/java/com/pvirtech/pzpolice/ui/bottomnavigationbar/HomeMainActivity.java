package com.pvirtech.pzpolice.ui.bottomnavigationbar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.file.down.DownloadActivity;
import com.pvirtech.pzpolice.file.down.DownloadService;
import com.pvirtech.pzpolice.main.AppLoginActivity;
import com.pvirtech.pzpolice.ui.activity.SettingActivity;
import com.pvirtech.pzpolice.utils.PreferenceUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class HomeMainActivity extends AppCompatActivity implements BottomNavigationBar
        .OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<Fragment> fragments;
    TaskFragment taskFragment = new TaskFragment();
    WorkFragment workFragment = new WorkFragment();
    PersonFragment personFragment = new PersonFragment();
    ScoreboardFragment scoreboardFragment = new ScoreboardFragment();
    /**
     * 设置显示主页
     */
    SettingFragment mSettingFragment = new SettingFragment();
    String[] titles = {"我的任务", "我的考勤", "人员簿", "积分榜"};
    Toolbar toolbar;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        setContentView(R.layout.activity_home_main);
        mContext = this;

        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titles[0]);//设置主标题
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string
                .app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id
                .bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        BadgeItem numberBadgeItem = new BadgeItem().setBorderWidth(4).setBackgroundColor(Color
                .RED).setText("5").setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.task, mContext.getResources
                ().getString(R.string.task)).setActiveColorResource(R.color.button_theme)
                .setBadgeItem(numberBadgeItem)).addItem(new BottomNavigationItem(R.mipmap
                .attendance, mContext.getResources().getString(R.string.attendance))
                .setActiveColorResource(R.color.button_theme)).addItem(new BottomNavigationItem(R
                .mipmap.mail_list, mContext.getResources().getString(R.string.person))
                .setActiveColorResource(R.color.button_theme)).addItem(new BottomNavigationItem(R
                .mipmap.scoreboard, mContext.getResources().getString(R.string.scoreboard))
                .setActiveColorResource(R.color.button_theme))
//                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp,
// "Games").setActiveColorResource(R.color.grey)
// .setBadgeItem(numberBadgeItem))
                .setFirstSelectedPosition(0).initialise();
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = HomeMainActivity.this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, taskFragment);
        transaction.commit();
    }


    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(taskFragment);
        fragments.add(workFragment);
        fragments.add(personFragment);
        fragments.add(scoreboardFragment);
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = HomeMainActivity.this.getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
                toolbar.setTitle(titles[position]);
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = HomeMainActivity.this.getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_information) {

        } else if (id == R.id.nav_download) {
            Intent intent = new Intent(mContext, DownloadActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_upload) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(mContext, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.cancellation) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("你确定注销吗?")
                    .setContentText("你将要注销该账户").setConfirmText("确定").setCancelText("取消")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    PreferenceUtils.setPrefString(mContext, "strLoginName", "");
                    Intent intent = new Intent(mContext, AppLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
