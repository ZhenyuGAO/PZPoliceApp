package com.pvirtech.pzpolice.main.bottomnavigationbar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.file.down.DownloadActivity;
import com.pvirtech.pzpolice.file.down.DownloadService;
import com.pvirtech.pzpolice.ui.activity.SettingActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class HomeMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<Fragment> fragments;
    TaskFragment taskFragment = new TaskFragment();
    WorkFragment workFragment = new WorkFragment();
    PersonFragment personFragment = new PersonFragment();
    ScoreboardFragment scoreboardFragment = new ScoreboardFragment();
    Toolbar toolbar;
    private Context mContext = null;


    private void onTableSelected(Fragment fragment, String title) {
        FragmentManager fm = HomeMainActivity.this.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()) {
            ft.replace(R.id.layFrame, fragment);
        } else {
            ft.add(R.id.layFrame, fragment);
        }
        ft.commitAllowingStateLoss();
        toolbar.setTitle(title);

       /* private void setDefaultFragment() {
            FragmentManager fm = HomeMainActivity.this.getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.layFrame, taskFragment);
            transaction.commit();
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        setContentView(R.layout.activity_home_main);
        mContext = this;

        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("我的任务");//设置主标题
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * 左侧抽提
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /**
         * 底部导航
         */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.task:
                        onTableSelected(taskFragment, "我的任务");
                        return true;
                    case R.id.attendance:
                        onTableSelected(workFragment, "我的考勤");
                        return true;
                    case R.id.mail_list:
                        onTableSelected(personFragment, "人员簿");
                        return true;
                    case R.id.scoreboard:
                        onTableSelected(scoreboardFragment, "积分榜");
                        return true;
                }
                return false;
            }
        });
        onTableSelected(taskFragment, "我的任务");
    }

    /**
     * 设置默认的
     */




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
        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
