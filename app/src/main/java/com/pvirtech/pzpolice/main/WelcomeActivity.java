package com.pvirtech.pzpolice.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.ImageView;

import com.pvirtech.pzpolice.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends Activity {
    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initDirPath();
//        ivWelcomeBg.setImageResource(R.mipmap.welcome_bg);
//        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        toHome();
    }

    private void toHome() {
        Thread mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, AppLoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        mThread.start();

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
                Snackbar.make(ivWelcomeBg, "SD卡未加载，不能保存",  Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(ivWelcomeBg, "目录获取失败，" + notice + "不能保存",  Snackbar.LENGTH_SHORT).show();
            }
            return null;
        }
    }
}
