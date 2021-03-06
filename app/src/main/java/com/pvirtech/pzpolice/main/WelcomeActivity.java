package com.pvirtech.pzpolice.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.bottomnavigationbar.HomeMainActivity;
import com.pvirtech.pzpolice.utils.PreferenceUtils;

import java.io.File;

import es.dmoral.toasty.Toasty;

public class WelcomeActivity extends Activity {

    private Context mContext;
    private String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mContext = WelcomeActivity.this;
        initDirPath();
        toHome();
    }

    private void toHome() {
        Intent intent = new Intent();
        String strLoginName = PreferenceUtils.getPrefString(mContext, "strLoginName", "");
        if (!TextUtils.isEmpty(strLoginName)) {
            intent.setClass(WelcomeActivity.this, HomeMainActivity.class);
        } else {
            intent.setClass(WelcomeActivity.this, AppLoginActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void initDirPath() {
        try {
//            File efile = getExternalFilesDir(null);
            // 缓存目录
            File cfile = getExternalCacheDir();
            if (cfile != null) {
                String epath = cfile.getAbsolutePath();
                // 数据库目录
                String dbPath = getDirPath(epath, "/db", "数据");
                AppValue.getInstance().setDbPath(dbPath);
                // 图片目录
                String imgPath = getDirPath(epath, "/img", "照片");
                AppValue.getInstance().setImgPath(imgPath);
                // APP安装包下载目录
                String updatePath = getDirPath(epath, "/update", "更新");
                AppValue.getInstance().setUpdatePath(updatePath);
            } else {
                Toasty.error(mContext, "SD卡未加载，不能保存").show();
            }
        } catch (Exception e) {
        }
    }

    private String getDirPath(String epath, String path, String notice) {
        String dirPath = epath + path;
        File file = new File(dirPath);
        boolean ok = file.exists();
        if (!ok) {
            ok = file.mkdir();
        }
        if (ok) {
            return dirPath;
        } else {
            if (!TextUtils.isEmpty(notice)) {
                Toasty.error(mContext, "目录获取失败，" + notice + "不能保存").show();
            }
            return null;
        }
    }
}
