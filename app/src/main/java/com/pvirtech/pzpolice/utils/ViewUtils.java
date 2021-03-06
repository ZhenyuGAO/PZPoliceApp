/******************************************************************************
 *       Project_Name    ： LeyiYun_PT
 *       Package         ： com.llymobile.pt.utils
 *
 *     Copyright 成都乐乐云科技有限公司 Corporation 2014 All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于乐乐云软件公司内部使用，禁止转发
 ******************************************************************************/
package com.pvirtech.pzpolice.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * <Pre>
 * View处理工具类
 * </Pre>
 *
 */
public class ViewUtils {
    /**
     * 重置AbsListView的高度.
     * item 的最外层布局要用 RelativeLayout,如果计算的不准，就为RelativeLayout指定一个高度
     *
     * @param absListView   the abs list view
     * @param lineNumber    每行几个  ListView一行一个item
     * @param verticalSpace the vertical space
     */
    public static void setAbsListViewHeight(AbsListView absListView, int lineNumber, int verticalSpace) {

        int totalHeight = getAbsListViewHeight(absListView, lineNumber, verticalSpace);
        ViewGroup.LayoutParams params = absListView.getLayoutParams();
        params.height = totalHeight;
        ((ViewGroup.MarginLayoutParams) params).setMargins(0, 0, 0, 0);
        absListView.setLayoutParams(params);
    }

    /**
     * 获取AbsListView的高度.
     *
     * @param absListView   the abs list view
     * @param lineNumber    每行几个  ListView一行一个item
     * @param verticalSpace the vertical space
     */
    public static int getAbsListViewHeight(AbsListView absListView, int lineNumber, int verticalSpace) {
        int totalHeight = 0;
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        absListView.measure(w, h);
        ListAdapter mListAdapter = absListView.getAdapter();
        if (mListAdapter == null) {
            return totalHeight;
        }

        int count = mListAdapter.getCount();
        if (absListView instanceof ListView) {
            for (int i = 0; i < count; i++) {
                View listItem = mListAdapter.getView(i, null, absListView);
                listItem.measure(w, h);
                totalHeight += listItem.getMeasuredHeight();
            }
            if (count == 0) {
                totalHeight = verticalSpace;
            } else {
                totalHeight = totalHeight + (((ListView) absListView).getDividerHeight() * (count - 1));
            }

        } else if (absListView instanceof GridView) {
            int remain = count % lineNumber;
            if (remain > 0) {
                remain = 1;
            }
            if (mListAdapter.getCount() == 0) {
                totalHeight = verticalSpace;
            } else {
                View listItem = mListAdapter.getView(0, null, absListView);
                listItem.measure(w, h);
                int line = count / lineNumber + remain;
                totalHeight = line * listItem.getMeasuredHeight() + (line - 1) * verticalSpace;
            }

        }
        return totalHeight;

    }

    /**
     * 测量view
     * 最后通过getMeasuredWidth()获取宽度和高度
     *
     * @param v 要测量的view
     */
    public static void measureView(View v) {
        if (v == null) {
            return;
        }
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
    }

    /**
     * 根据分辨率获得字体大小
     * @param screenWidth  屏幕宽
     * @param screenHeight 屏幕高
     * @param textSize     文字大小
     * @return 适配之后的字体大小
     */
    public static int resizeTextSize(int screenWidth, int screenHeight, int textSize) {
        float ratio = 1;
        try {
            float ratioWidth = (float) screenWidth / 480;
            float ratioHeight = (float) screenHeight / 800;
            ratio = Math.min(ratioWidth, ratioHeight);
        } catch (Exception e) {
        }
        return Math.round(textSize * ratio);
    }
}
