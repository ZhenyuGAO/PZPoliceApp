package com.pvirtech.pzpolice.main.bottomnavigationbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.third.baidu.IndoorLocationActivity;
import com.pvirtech.pzpolice.ui.activity.LeaveActivity;
import com.pvirtech.pzpolice.ui.adapter.TaskMenuAdapter;
import com.pvirtech.pzpolice.ui.base.BaseFragment;

import butterknife.BindView;



/**
 * 工作主页
 */
public class WorkFragment extends BaseFragment {
    @BindView(R.id.task)
    RecyclerView task;
    private Context mContext = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        mContext = getActivity();
        initView(view);
        TaskMenuAdapter taskMenuAdapter = new TaskMenuAdapter(mContext, new TaskMenuAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, LeaveActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mContext, LeaveActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        /*intent= new Intent(mContext, LeaveActivity.class);
                        startActivity(intent);*/
                        break;
                    case 3:
                        intent = new Intent(mContext, IndoorLocationActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(int position) {

                return false;
            }
        });
        task.setLayoutManager(new LinearLayoutManager(mContext));
        /*task.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        task.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));*/
        task.setLayoutManager(new GridLayoutManager(mContext, 4));
        task.setAdapter(taskMenuAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
