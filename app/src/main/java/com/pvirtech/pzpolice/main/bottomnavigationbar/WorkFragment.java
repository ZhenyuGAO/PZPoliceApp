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
import com.pvirtech.pzpolice.entity.Icon;
import com.pvirtech.pzpolice.third.baidu.IndoorLocationActivity;
import com.pvirtech.pzpolice.ui.activity.LeaveActivity;
import com.pvirtech.pzpolice.ui.activity.MonthlyCalendarActivity;
import com.pvirtech.pzpolice.ui.activity.SickLeaveActivity;
import com.pvirtech.pzpolice.ui.adapter.TaskMenuAdapter;
import com.pvirtech.pzpolice.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 工作主页
 */
public class WorkFragment extends BaseFragment {
    @BindView(R.id.task)
    RecyclerView task;
    private Context mContext = null;
    private List<Icon> mData = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        mContext = getActivity();
        initView(view);


        Icon declare = new Icon(mContext.getResources().getString(R.string.time_to_declare), R.mipmap.declare);//时间申报
        Icon leave = new Icon(mContext.getResources().getString(R.string.leave), R.mipmap.leave);//请假
        Icon sickLeave = new Icon(mContext.getResources().getString(R.string.sick_leave), R.mipmap.sick_leave);//销假
        Icon position = new Icon(mContext.getResources().getString(R.string.position), R.mipmap.position);//我的位置
        Icon calendar = new Icon(mContext.getResources().getString(R.string.calendlar), R.mipmap.calendar);//我的位置
        mData = new ArrayList<>();
        mData.add(declare);
        mData.add(leave);
        mData.add(sickLeave);
        mData.add(position);
        mData.add(calendar);
        TaskMenuAdapter taskMenuAdapter = new TaskMenuAdapter(mContext, mData, new TaskMenuAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                String strSelected = mData.get(position).getName();
                if (strSelected.equals(mContext.getResources().getString(R.string.time_to_declare))) {
                    intent = new Intent(mContext, LeaveActivity.class);
                    startActivity(intent);
                } else if (strSelected.equals(mContext.getResources().getString(R.string.leave))) {
                    intent = new Intent(mContext, LeaveActivity.class);
                    startActivity(intent);
                } else if (strSelected.equals(mContext.getResources().getString(R.string.sick_leave))) {
                    intent = new Intent(mContext, SickLeaveActivity.class);
                    startActivity(intent);
                } else if (strSelected.equals(mContext.getResources().getString(R.string.position))) {
                    intent = new Intent(mContext, IndoorLocationActivity.class);
                    startActivity(intent);
                } else if (strSelected.equals(mContext.getResources().getString(R.string.calendlar))) {
                    intent = new Intent(mContext, MonthlyCalendarActivity.class);
                    startActivity(intent);
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
