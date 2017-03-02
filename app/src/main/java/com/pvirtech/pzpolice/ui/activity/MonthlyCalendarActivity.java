package com.pvirtech.pzpolice.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.entity.WorkDay;
import com.pvirtech.pzpolice.enumeration.DayStatus;
import com.pvirtech.pzpolice.enumeration.WorkStatus;
import com.pvirtech.pzpolice.ui.adapter.CalendarAdapter;
import com.pvirtech.pzpolice.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MonthlyCalendarActivity extends BaseActivity {
    @BindView(R.id.tv_pre)
    TextView tvPre;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_next)
    TextView tvNext;
    private Context mContext = null;

    static String[] weeks = {"一", "二", "三", "四", "五", "六", "日"};
    static String[] dayArray = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    @BindView(R.id.recyle_calendar)
    RecyclerView recyleCalendar;
    private List<WorkDay> mData = new ArrayList<>();
    CalendarAdapter taskMenuAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_calendar);
        initTitleView("我的工作");
        mContext = this;

        String[] weeks = {"一", "二", "三", "四", "五", "六", "日"};
        for (String data : weeks) {
            WorkDay workDay = new WorkDay();
            workDay.setText(data);
            workDay.setDayStatus(DayStatus.WEEK.getValue());
            workDay.setWorkStatus(WorkStatus.EMPTY.getValue());
            mData.add(workDay);
        }
        //获取前月的第一天
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, 2017);
        //设置月份
        cal.set(Calendar.MONTH, 2 - 1);
        //设置日历中月份的第1天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int firstWeekOfDay = cal.get(Calendar.DAY_OF_WEEK);
        firstWeekOfDay = (firstWeekOfDay + 6) % 7;
        System.out.println(firstWeekOfDay);
        for (int i = 1; i < firstWeekOfDay; i++) {
            WorkDay workDay = new WorkDay();
            workDay.setText("");
            workDay.setDayStatus(DayStatus.EMPTY.getValue());
            workDay.setWorkStatus(WorkStatus.EMPTY.getValue());
            mData.add(workDay);
        }
        for (int i = 1; i < 31; i++) {
            WorkDay workDay = new WorkDay();
            workDay.setText(String.valueOf(i));
            workDay.setDayStatus(DayStatus.NORMAL.getValue());
            if (i == 2) {
                workDay.setDayStatus(DayStatus.TODAY.getValue());
            }
            if (i == 3) {
                workDay.setDayStatus(DayStatus.SELECTED.getValue());
            }
            mData.add(workDay);
        }
        taskMenuAdapter = new CalendarAdapter(mContext, mData,firstWeekOfDay+7-1 ,new CalendarAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public boolean onItemLongClick(int position) {
                System.out.println("aaaaaaa");
                return false;
            }
        });
        recyleCalendar.setLayoutManager(new LinearLayoutManager(mContext));

        recyleCalendar.setLayoutManager(new GridLayoutManager(mContext, 7));
        recyleCalendar.setAdapter(taskMenuAdapter);
    }

    @OnClick({R.id.tv_pre, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pre:
                break;
            case R.id.tv_next:
                break;
        }
    }
}
