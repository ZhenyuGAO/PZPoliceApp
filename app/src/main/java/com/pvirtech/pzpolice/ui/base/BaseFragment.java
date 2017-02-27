package com.pvirtech.pzpolice.ui.base;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pvirtech.pzpolice.R;

import butterknife.ButterKnife;

/**
 * Created by youpengda on 2017/2/8.
 */

public class BaseFragment extends Fragment {
    public void initView(View view) {
        ButterKnife.bind(this, view);//绑定framgent
    }

    public void initTitleView(View view, String title) {
        ButterKnife.bind(this, view);//绑定framgent
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(title);//设置主标题
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
      /*  ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

}
