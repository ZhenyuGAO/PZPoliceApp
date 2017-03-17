package com.pvirtech.pzpolice.ui.bottomnavigationbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.http.HttpResult;
import com.pvirtech.pzpolice.http.RetrofitHttp;
import com.pvirtech.pzpolice.test.one.CaseQueryEntity;
import com.pvirtech.pzpolice.ui.activity.TaskAdapter;
import com.pvirtech.pzpolice.ui.base.BaseFragment;
import com.pvirtech.pzpolice.utils.L;
import com.pvirtech.pzpolice.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 任务主页
 */
public class TaskFragment extends BaseFragment {

    private Context mContext;
    String tag = "CaseQueryActivity";
    List<CaseQueryEntity> mCaseQueryEntityList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View notLoadingView;
    private final int TOTAL_COUNTER = 18;
    private final int PAGE_SIZE = 6;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private FloatingActionMenu menu_red;
    private int pageindex = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mContext = getActivity();
        initView(view);
        FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_red.hideMenuButton(true);
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void initView(View view) {
        menu_red = (FloatingActionMenu) view.findViewById(R.id.menu_red);

        /**/
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(pageindex, true);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initAdapter();
        addHeadView();
        mRecyclerView.setAdapter(mTaskAdapter);


        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.d("onScrolled", String.valueOf(dy));
                if (dy > 2) {
                    menu_red.hideMenuButton(true);
                } else if (dy < -2) {
                    menu_red.showMenuButton(true);
                    if (menu_red.isOpened()) {
                        menu_red.toggle(true);
                    }
                }
            }
        });

    }

    private void addHeadView() {
        View headView = getActivity().getLayoutInflater().inflate(R.layout.head_view, (ViewGroup)
                mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.tv)).setText("我的工作任务");
        mTaskAdapter.addHeaderView(headView);
    }


    private void initAdapter() {
        mTaskAdapter = new TaskAdapter(PAGE_SIZE, mCaseQueryEntityList);
//        View emptyView = getLayoutInflater().inflate(R.layout.no_result_view, (ViewGroup)
// mRecyclerView.getParent(), false);
//        mQuickAdapter.setEmptyView(emptyView);
        mTaskAdapter.openLoadAnimation(PreferenceUtils.getPrefInt(mContext, "RecycleAnim", 0));
        mTaskAdapter.isFirstOnly(!PreferenceUtils.getPrefBoolean(mContext, "isFirstOnly", false));
        mTaskAdapter.openLoadAnimation();
        mTaskAdapter.openLoadMore(PAGE_SIZE);
        mRecyclerView.setAdapter(mTaskAdapter);
        mCurrentCounter = mTaskAdapter.getData().size();
        mTaskAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(pageindex, false);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    private void getData(int pageindex, boolean blnIsRefresh) {
        CaseQueryEntity caseQueryEntity = new CaseQueryEntity("我的贴罚单任务",
                "川交高执测试大队罚[2016]3号", "", "2016年08月24日17时22分", "调查取证",
                "20160824172331bbdc98380111", "0", "JT51GSJT51GStest", "待办");

        for (int i = 0; i < 15; i++) {
            mCaseQueryEntityList.add(caseQueryEntity);
        }


        final boolean isRefresh = blnIsRefresh;
//        llloading.setVisibility(View.VISIBLE);
        final int mPage = pageindex;
        String departid/* = AppValue.getInstance().getUserInfo().getOrganID()*/;
        departid = "JT51GSJT51GStest";
        Map map = new HashMap();
        map.put("depart_id", departid);
        map.put("ly", "");
        map.put("currentPage", String.valueOf(pageindex));
        map.put("showCount", "10");

        Subscription subscription01 = RetrofitHttp.provideClientApi().CaseQueryData(map)
                .doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CaseQueryEntity>>>() {
            @Override
            public void onCompleted() {
                Log.d(tag, "onCompleted");
                if (isRefresh) {
                    mTaskAdapter.setNewData(mCaseQueryEntityList);
                    mTaskAdapter.openLoadMore(PAGE_SIZE);
                    mTaskAdapter.removeAllFooterView();
                    mCurrentCounter = PAGE_SIZE;
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    if (mCurrentCounter >= TOTAL_COUNTER) {
                        mTaskAdapter.loadComplete();
                        if (notLoadingView == null) {
                            notLoadingView = getActivity().getLayoutInflater().inflate(R.layout
                                    .not_loading, (ViewGroup) mRecyclerView.getParent(), false);
                        }
                        mTaskAdapter.addFooterView(notLoadingView);
                    } else {
                        mTaskAdapter.addData(mCaseQueryEntityList);
                        mCurrentCounter = mTaskAdapter.getData().size();
                        mTaskAdapter.hideLoadingMore();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, e.toString());
                if (isRefresh) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    mTaskAdapter.showLoadMoreFailedView();
                }
            }

            @Override
            public void onNext(HttpResult<List<CaseQueryEntity>> response) {
                if (response.getResultCode().equals("0000")) {
                    mCaseQueryEntityList = response.getData();
                    L.d("sucess");
                } else if (response.getResultCode().equals("9999")) {
                    L.d("FAIL");
                }
            }
        });


    }
}
