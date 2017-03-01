package com.pvirtech.pzpolice.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.entity.Icon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youpengda on 2017/2/9.
 */

public class TaskMenuAdapter extends RecyclerView.Adapter {


    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private Context mContext = null;

    private List<Icon> mData = null;

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public TaskMenuAdapter(Context context, OnRecyclerViewListener onRecyclerViewListener) {
        Icon declare = new Icon(context.getResources().getString(R.string.time_to_declare), R.mipmap.declare);//时间申报
        Icon leave = new Icon(context.getResources().getString(R.string.leave), R.mipmap.leave);//请假
        Icon sickLeave = new Icon(context.getResources().getString(R.string.sick_leave), R.mipmap.sick_leave);//销假


//        Icon sign = new Icon(context.getResources().getString(R.string.sign), R.mipmap.sign);
        mData = new ArrayList<>();
        mData.add(declare);
        mData.add(leave);
        mData.add(sickLeave);
        this.onRecyclerViewListener = onRecyclerViewListener;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.icon_layout, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.icon.setImageResource(mData.get(i).getIcon());
        holder.name.setText(mData.get(i).getName());
        holder.position = i;
//        showItemAnim(holder.cardView, i);
    }

    @Override
    public int getItemCount() {
        int a = mData.size();
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView icon;
        TextView name;
        LinearLayout rootView;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = (LinearLayout) itemView.findViewById(R.id.root_view);
            name = (TextView) itemView.findViewById(R.id.name);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
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