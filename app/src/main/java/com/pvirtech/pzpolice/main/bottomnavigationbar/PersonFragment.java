package com.pvirtech.pzpolice.main.bottomnavigationbar;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.ui.base.BaseFragment;
import com.pvirtech.pzpolice.ui.holder.FirstTreeItemHolder;
import com.pvirtech.pzpolice.ui.holder.SecondTreeItemHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * 人员薄主页
 */
public class PersonFragment extends BaseFragment {
    private Context mContext = null;
    private AndroidTreeView tView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        mContext = getActivity();
        initView(view);

        ViewGroup containerView = (ViewGroup) view.findViewById(R.id.container);
        TreeNode root = TreeNode.root();
        TreeNode socialNetworks = new TreeNode(new FirstTreeItemHolder.IconTreeItem(R.mipmap.person, "Social")).setViewHolder(new FirstTreeItemHolder(getActivity()));
        TreeNode facebook = new TreeNode(new SecondTreeItemHolder.IconTreeItem(R.mipmap.person, "Social1")).setViewHolder(new SecondTreeItemHolder(getActivity()));
        TreeNode linkedin = new TreeNode(new SecondTreeItemHolder.IconTreeItem(R.mipmap.person, "Social2")).setViewHolder(new SecondTreeItemHolder(getActivity()));
        TreeNode google = new TreeNode(new SecondTreeItemHolder.IconTreeItem(R.mipmap.person, "Social3")).setViewHolder(new SecondTreeItemHolder(getActivity()));
        TreeNode twitter = new TreeNode(new SecondTreeItemHolder.IconTreeItem(R.mipmap.person, "Social4")).setViewHolder(new SecondTreeItemHolder(getActivity()));
        socialNetworks.addChildren(facebook, google, twitter, linkedin);
        root.addChildren(socialNetworks);
        tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        containerView.addView(tView.getView());
        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
