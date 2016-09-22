package com.androidbootstrap.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.androidbootstrap.R;
import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.base.activity.BaseActivity;
import com.androidbootstrap.util.LogUtil;
import com.library.ui.view.MultiStateView;
import com.library.ui.view.SmartRecyclerView;

import java.util.List;

import butterknife.BindView;
import hugo.weaving.DebugLog;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public class TestListActivity_bak extends BaseActivity<TestListPresenter> implements ITestListView{
    @BindView(R.id.srv)
    SmartRecyclerView  smartRecyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.multiStateView)
    MultiStateView     multiStateView;
    private TestListAdapter adapter;
    int curPage = 1;

    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
//        activityComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        adapter = new TestListAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        smartRecyclerView.setLayoutManager(linearLayoutManager);
        smartRecyclerView.setAdapter(adapter);
        smartRecyclerView.setAutoLoadMoreEnable(true);
        presenter.loadName(Constants.LoadType.FIRST_IN);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        super.initEvent(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                presenter.loadName(Constants.LoadType.REFRESH);
            }
        });

        smartRecyclerView.setOnRecycleViewLoadMoreListener(new SmartRecyclerView.OnRecycleViewLoadMoreListener() {
            @Override
            public void onLoadMore() {
                curPage++;
                presenter.loadName(Constants.LoadType.LOAD_MORE);
            }
        });

    }

    @Override
    public void displayDialog(int dialogType) {

    }

    @Override
    public void hideDialog(int... dialogType) {

    }




    @Override
    public void setViewState(int state) {
        multiStateView.setViewState(state);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setLoadMoreFinish(boolean hasMore) {
        smartRecyclerView.setLoadMoreFinish(hasMore);
    }



    @DebugLog
    @Override
    public void onSuccess(List names,int loadType) {
        switch (loadType){
            case Constants.LoadType.FIRST_IN:
                adapter.setData(names);
                LogUtil.d("FIRST_IN");
                break;
            case Constants.LoadType.REFRESH:
                adapter.setData(names);
                LogUtil.d("REFRESH");
                break;
            case Constants.LoadType.LOAD_MORE:
                adapter.addData(names);
                LogUtil.d("LOAD_MORE");
                break;
        }
        LogUtil.d(adapter.getData());
        LogUtil.d(adapter.getData().size());
        adapter.notifyDataSetChanged();
    }
}
