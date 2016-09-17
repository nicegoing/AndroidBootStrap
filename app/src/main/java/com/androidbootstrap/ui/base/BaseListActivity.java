package com.androidbootstrap.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.androidbootstrap.R;
import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.util.LogUtil;
import com.library.ui.view.MultiStateView;
import com.library.ui.view.SmartRecyclerView;

import java.util.List;

import butterknife.BindView;
import hugo.weaving.DebugLog;

/**
 * 该基类封装了基本的刷新，加载功能
 * 1.子类的View接口需要继承IListView
 * 2.
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/17
 * @since 1.0
 */
public abstract class BaseListActivity<P extends IPresenter, A extends BaseListAdapter> extends BaseActivity<P> implements IListView {
    @BindView(R.id.srv)
    SmartRecyclerView  smartRecyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.multiStateView)
    MultiStateView     multiStateView;
    int curPage = 1;
    A adapter;

    @Override
    protected final int getLayoutId() {
        return R.layout.activity_list;
    }

    @CallSuper
    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        adapter = getAdapter();
        smartRecyclerView.setLayoutManager(getLayoutManger());
        addItemDecoration(getItemDecoration());
        smartRecyclerView.setAdapter(adapter);
        smartRecyclerView.setAutoLoadMoreEnable(true);
        loadData(Constants.LoadType.FIRST_IN);
    }


    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        super.initEvent(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                loadData(Constants.LoadType.REFRESH);
            }
        });

        smartRecyclerView.setOnRecycleViewLoadMoreListener(new SmartRecyclerView.OnRecycleViewLoadMoreListener() {
            @Override
            public void onLoadMore() {
                curPage++;
                loadData(Constants.LoadType.LOAD_MORE);
            }
        });

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
    public void onSuccess(List data, int loadType) {
        switch (loadType) {
            case Constants.LoadType.FIRST_IN:
                adapter.setData(data);
                LogUtil.d("FIRST_IN");
                break;
            case Constants.LoadType.REFRESH:
                adapter.setData(data);
                LogUtil.d("REFRESH");
                break;
            case Constants.LoadType.LOAD_MORE:
                adapter.addData(data);
                LogUtil.d("LOAD_MORE");
                break;
        }
        LogUtil.d(adapter.getData());
        LogUtil.d(adapter.getData().size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayDialog(int dialogType) {

    }

    @Override
    public void hideDialog(int... dialogType) {

    }


    protected final void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if (itemDecoration != null) {
            smartRecyclerView.addItemDecoration(itemDecoration);
        }
    }

    /**
     * 获取适配器对象
     *
     * @return 返回所需的适配器对象
     */
    @NonNull
    protected abstract A getAdapter();

    /**
     * 子类继承该方法，发送加载数据的请求。子类的请求使用ListSubscriber做对返回数据做处理
     *
     * @param loadType 数据请求类型，包括{@link com.androidbootstrap.constant.Constants.LoadType}的三种类型
     */
    protected abstract void loadData(int loadType);

    /**
     * 获取recyclerView 的布局管理器
     *
     * @return recyclerView的布局管理器
     */
    @NonNull
    protected abstract RecyclerView.LayoutManager getLayoutManger();

    /**
     * 获取ItemDecoration
     *
     * @return ItemDecoration
     */
    protected abstract RecyclerView.ItemDecoration getItemDecoration();
}
