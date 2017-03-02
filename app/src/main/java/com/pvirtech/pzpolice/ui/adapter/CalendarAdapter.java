package com.pvirtech.pzpolice.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.entity.WorkDay;
import com.pvirtech.pzpolice.enumeration.DayStatus;
import com.pvirtech.pzpolice.utils.ScreenUtils;

import java.util.List;


/**
 * Created by youpengda on 2017/2/9.
 */

public class CalendarAdapter extends RecyclerView.Adapter {


    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private Context mContext = null;

    private List<WorkDay> mData = null;
    private int notCal;
    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public CalendarAdapter(Context context, List<WorkDay> mData, int notCal, OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
        this.mData = mData;
        mContext = context;
        this.notCal = notCal;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendar_layout, null);
        int width = (ScreenUtils.getScreenWidth(mContext) - 80) / 7;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        WorkDay workDay = mData.get(i);
        holder.text.setText(workDay.getText());
        if (workDay.getDayStatus() == DayStatus.NORMAL.getValue()) {
            holder.text.setTextColor(0xFF8696A5);
            holder.calendar_root_view.setBackgroundResource(R.drawable.day_normoal_background);
        } else if (workDay.getDayStatus() == DayStatus.TODAY.getValue()) {
            holder.text.setTextColor(0xFF457BF4);
            holder.calendar_root_view.setBackgroundResource(R.drawable.day_normoal_background);
        } else if (workDay.getDayStatus() == DayStatus.SELECTED.getValue()) {
            holder.text.setTextColor(0xFFFAFBFE);
            holder.calendar_root_view.setBackgroundResource(R.drawable.day_selected_background);
        }
        if (workDay.getDayStatus() == DayStatus.EMPTY.getValue()) {
            holder.text.setTextColor(0xFF4586ED);
            holder.calendar_root_view.setBackgroundResource(R.drawable.day_week_background);
            holder.workStatus.setVisibility(View.INVISIBLE);
        }
        if (workDay.getDayStatus() == DayStatus.WEEK.getValue()) {
            holder.workStatus.setVisibility(View.INVISIBLE);
            holder.text.setTextColor(0xFF4586ED);
            holder.calendar_root_view.setBackgroundResource(R.drawable.day_week_background);
        }
        holder.position = i;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView text;
        TextView workStatus;
        LinearLayout calendar_root_view;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            calendar_root_view = (LinearLayout) itemView.findViewById(R.id.calendar_root_view);
            text = (TextView) itemView.findViewById(R.id.text);
            workStatus = (TextView) itemView.findViewById(R.id.work_status);
            calendar_root_view.setOnClickListener(this);
            calendar_root_view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                if (position < notCal) {
                    return;
                }
                for (WorkDay workDay : mData) {
                    if (workDay.getDayStatus() == DayStatus.SELECTED.getValue()) {
                        workDay.setDayStatus(DayStatus.NORMAL.getValue());
                    }
                }
                mData.get(position).setDayStatus(DayStatus.SELECTED.getValue());
                onRecyclerViewListener.onItemClick(position);
                notifyDataSetChanged();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onRecyclerViewListener) {
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}