package com.pvirtech.pzpolice.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pvirtech.pzpolice.R;
import com.unnamed.b.atv.model.TreeNode;


/**
 * Created by Bogdan Melnychuk on 2/12/15.
 */
public class SecondTreeItemHolder extends TreeNode.BaseNodeViewHolder<SecondTreeItemHolder.IconTreeItem> {
    private TextView tvTitle;
    private ImageView iv_Nav;

    public SecondTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_tree_second, null, false);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(value.text);

        ImageView iconView = (ImageView) view.findViewById(R.id.iv_icon);
        iconView.setImageResource(value.icon);
        iv_Nav = (ImageView) view.findViewById(R.id.iv_nav);
        if (node.isLeaf()) {
            iv_Nav.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override
    public void toggle(boolean active) {
        System.out.println("aaaaaaaaa");
        iv_Nav.setImageResource(active ? R.mipmap.down : R.mipmap.right);
    }

    public static class IconTreeItem {
        public int icon;
        public String text;

        public IconTreeItem(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }
    }
}