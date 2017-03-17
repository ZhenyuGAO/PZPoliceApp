package com.pvirtech.pzpolice.test.one;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.pvirtech.pzpolice.R;
import com.pvirtech.pzpolice.http.HttpResult;
import com.pvirtech.pzpolice.http.RetrofitHttp;
import com.pvirtech.pzpolice.ui.base.BaseActivity;
import com.pvirtech.pzpolice.utils.L;
import com.pvirtech.pzpolice.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CaseQueryActivity extends BaseActivity {

    //    @BindView(ll_loading)
//    LinearLayout llloading;
    @BindView(R.id.ll_no_result)
    LinearLayout llNoResult;
    //    @BindView(R.id.loadView)
//    LoadingView loadView;
    private Context mContext;
    private CompositeSubscription compositeSubscription;
    String tag = "CaseQueryActivity";
    private int pageindex = 1;
    List<CaseQueryEntity> mCaseQueryEntityList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View notLoadingView;
    private final int TOTAL_COUNTER = 18;
    private final int PAGE_SIZE = 6;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private FloatingActionMenu menu_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_query);
        initTitleView("烟草稽查任务");
        compositeSubscription = new CompositeSubscription();
        mContext = this;
//        LoadingViewProgress.showDialogForLoading(mContext, null, false);
        initView();
        getData(pageindex, true);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_red.hideMenuButton(true);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        loadView = null;
        mContext = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_scan) {
//            Intent intent = new Intent(mContext, ScanActivity.class);
//            startActivity(intent);
//        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {
        menu_red = (FloatingActionMenu) findViewById(R.id.menu_red);

        /**/
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(pageindex, true);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView();
        mRecyclerView.setAdapter(mQuickAdapter);


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
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.tv)).setText("烟草稽查任务");
        mQuickAdapter.addHeaderView(headView);
    }


    private void initAdapter() {
        mQuickAdapter = new QuickAdapter(PAGE_SIZE, mCaseQueryEntityList);
//        View emptyView = getLayoutInflater().inflate(R.layout.no_result_view, (ViewGroup) mRecyclerView.getParent(), false);
//        mQuickAdapter.setEmptyView(emptyView);
        mQuickAdapter.openLoadAnimation(PreferenceUtils.getPrefInt(mContext, "RecycleAnim", 0));
        mQuickAdapter.isFirstOnly(!PreferenceUtils.getPrefBoolean(mContext, "isFirstOnly", false));
        mQuickAdapter.openLoadAnimation();
        mQuickAdapter.openLoadMore(PAGE_SIZE);
        mRecyclerView.setAdapter(mQuickAdapter);
        mCurrentCounter = mQuickAdapter.getData().size();
        mQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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


    /**
     * @param pageindex
     * @param blnIsRefresh
     */
    private void getData(int pageindex, boolean blnIsRefresh) {
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
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CaseQueryEntity>>>() {
                               @Override
                               public void onCompleted() {
                                   Log.d(tag, "onCompleted");
                                   if (isRefresh) {
                                       mQuickAdapter.setNewData(mCaseQueryEntityList);
                                       mQuickAdapter.openLoadMore(PAGE_SIZE);
                                       mQuickAdapter.removeAllFooterView();
                                       mCurrentCounter = PAGE_SIZE;
                                       mSwipeRefreshLayout.setRefreshing(false);
                                   } else {
                                       if (mCurrentCounter >= TOTAL_COUNTER) {
                                           mQuickAdapter.loadComplete();
                                           if (notLoadingView == null) {
                                               notLoadingView = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup)
                                                               mRecyclerView.getParent(),
                                                       false);
                                           }
                                           mQuickAdapter.addFooterView(notLoadingView);
                                       } else {
                                           mQuickAdapter.addData(mCaseQueryEntityList);
                                           mCurrentCounter = mQuickAdapter.getData().size();
                                           mQuickAdapter.hideLoadingMore();
                                       }
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.d(tag, e.toString());
                                   if (isRefresh) {
                                       mSwipeRefreshLayout.setRefreshing(false);
                                   } else {
                                       mQuickAdapter.showLoadMoreFailedView();
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
                           }
                );
        compositeSubscription.add(subscription01);
        compositeSubscription.remove(subscription01);

    }

    private void getData2(int pageindex, boolean blnIsRefresh) {


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
        compositeSubscription.add(//
                RetrofitHttp.provideClientApi().CaseQueryData(map)
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                int a = 0;
                                L.d("sucess");
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<HttpResult<List<CaseQueryEntity>>>() {
                            @Override
                            public void call(HttpResult<List<CaseQueryEntity>> response) {
                                String s = response.getResultCode();
                                response.getData();
                                L.d("sucess");
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                L.d("sucess");
                            }
                        }));


    }

    @OnClick({R.id.ll_no_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_no_result:
                break;
        }
    }


}
