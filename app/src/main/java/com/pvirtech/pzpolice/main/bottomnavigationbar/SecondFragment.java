package com.pvirtech.pzpolice.main.bottomnavigationbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseFragment;

/**
 * 个人中心主页
 */
public class SecondFragment extends BaseFragment {
    private Context mContext = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mContext = getActivity();
        initView(view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
