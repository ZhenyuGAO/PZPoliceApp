package com.pvirtech.pzpolice.main.bottomnavigationbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kyleduo.switchbutton.SwitchButton;
import com.pvirtech.pzpolice.ui.activity.MonthlyCalendarActivity;
import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.main.one.CaseQueryActivity;
import com.pvirtech.pzpolice.test.view.CollapsingToolbarLayoutActivity;
import com.pvirtech.pzpolice.ui.base.BaseFragment;
import com.pvirtech.pzpolice.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.pvirtech.pzpolice.R.id.switch_anim;


/**
 * Created by codeest on 16/8/23.
 */

public class SettingFragment extends BaseFragment {
    @BindView(switch_anim)
    SwitchButton switchAnim;
    private Context mContext = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mContext = getActivity();
       initView(view);
        switchAnim.setChecked(PreferenceUtils.getPrefBoolean(mContext, "isFirstOnly", false));
        return view;
    }

    @OnClick({R.id.ll_recycle_anim, switch_anim, R.id.ll_setting_update, R.id.ll_setting_clear, R.id.ll_recycle_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_recycle_anim:
                dialogRecycleAnim();
                break;
            case switch_anim:
                if (switchAnim.isChecked()) {
                    PreferenceUtils.setPrefBoolean(mContext, "isFirstOnly", true);
                } else {
                    PreferenceUtils.setPrefBoolean(mContext, "isFirstOnly", false);
                }
                break;
            case R.id.ll_setting_update:
                Intent intent = new Intent(mContext, CaseQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_setting_clear:
                Intent intent2 = new Intent(mContext, MonthlyCalendarActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_recycle_down:
                Intent intent3 = new Intent(mContext, CollapsingToolbarLayoutActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    private void dialogRecycleAnim() {
        final int items[] = {BaseQuickAdapter.ALPHAIN, BaseQuickAdapter.SCALEIN, BaseQuickAdapter.SLIDEIN_BOTTOM, BaseQuickAdapter
                .SLIDEIN_LEFT, BaseQuickAdapter.SLIDEIN_RIGHT};
        String item[] = {"渐现载入", "卡片载入", "底部载入", "左侧载入", "右侧载入"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  //先得到构造器
//        builder.setTitle(R.string.setting_recycleview); //设置标题
        View mTitleView = LayoutInflater.from(mContext).inflate(R.layout.dialog_title, null);
        builder.setCustomTitle(mTitleView);
//        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可

        builder.setSingleChoiceItems(item, PreferenceUtils.getPrefInt(mContext, "RecycleAnim", items[0]) - 1, new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceUtils.setPrefInt(mContext, "RecycleAnim", items[which]);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}

