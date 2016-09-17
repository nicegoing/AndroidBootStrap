package com.library.ui.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.R;

import java.lang.reflect.Field;

/**
 * 自定义RecyclerView，该类除了系统基本的功能以外，还加入了下拉加载更多等功能
 */
public final class SmartRecyclerView extends RecyclerView {
    private static final String  TAG             = "SmartRecyclerView";
    /**
     * item 类型
     */
    public final static  int     TYPE_HEADER     = Integer.MAX_VALUE + 10;//头部--支持头部增加一个headerView
    public final static  int     TYPE_FOOTER     = Integer.MAX_VALUE + 11;//底部--往往是loading_more
    private              boolean mIsFooterEnable = false;//是否允许加载更多
    private boolean         mIsHeaderEnable;
    /**
     * 自定义实现了头部和底部加载更多的adapter
     */
    private AutoLoadAdapter mAutoLoadAdapter;
    /**
     * 标记是否正在加载更多，防止再次调用加载更多接口
     */
    private boolean         mIsLoadingMore;

    /**
     * 是否第一次加载显示Loading
     */
    private boolean                       isFirstLoadGone;
    /**
     * 标记加载更多的position
     */
    private int                           mLoadMorePosition;
    /**
     * 加载更多的监听-业务需要实现加载数据
     */
    private OnRecycleViewLoadMoreListener mListener;

    /**
     * 是否预加载
     *
     * @author dingpeihua
     * @date 2016/4/25 8:55
     * @version 1.0
     */
    private boolean isPreLoading;
    //    private boolean loadMoreFinished = true;
    /**
     * 是否能加载更多,仅跟踪RecycleView滚动方向
     */
    private boolean canLoadMore = false;


    public SmartRecyclerView(Context context) {
        this(context, null);
    }

    public SmartRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void setPreLoading(boolean isPreLoading) {
        this.isPreLoading = isPreLoading;
    }

    public void setFirstLoadGone(boolean firstLoadGone) {
        isFirstLoadGone = firstLoadGone;
    }

    /**
     * 添加头部view
     *
     * @param resId
     */
    public void addHeaderView(int resId) {
        mAutoLoadAdapter.addHeaderView(resId);
    }

    public void addHeaderView(View headView) {
        mAutoLoadAdapter.addHeaderView(headView);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mAutoLoadAdapter = new AutoLoadAdapter(adapter);
            try {
                adapter.unregisterAdapterDataObserver(dataObserver);
            } catch (Exception e) {
            }
            adapter.registerAdapterDataObserver(dataObserver);
        }
        super.swapAdapter(mAutoLoadAdapter, true);
    }

    RecyclerViewDataObserver dataObserver = null;

    private class RecyclerViewDataObserver extends AdapterDataObserver {
        AdapterDataObserver viewDataObserver;

        public Field getDeclaredField(Class clazz, String fieldName) {
            Field field = null;
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    return field;
                } catch (Exception e) {
                }
            }
            return null;
        }

        public RecyclerViewDataObserver() {
            try {
                //                //通过反射获取父类观察者对象
                //                Field declaredField = RecyclerView.class.getDeclaredField("mObserver");
                //                declaredField.setAccessible(true);

                Field declaredField = getDeclaredField(RecyclerView.class, "mObserver");
                if (declaredField != null) {
                    viewDataObserver = (AdapterDataObserver) declaredField.get(SmartRecyclerView.this);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onChanged() {
            if (viewDataObserver != null) {
                viewDataObserver.onChanged();
            }
            setLoadMoreFinish(mIsFooterEnable);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            if (viewDataObserver != null) {
                viewDataObserver.onItemRangeChanged(positionStart, itemCount);
            }
            setLoadMoreFinish(mIsFooterEnable);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            if (viewDataObserver != null) {
                viewDataObserver.onItemRangeChanged(positionStart, itemCount, payload);
            }
            setLoadMoreFinish(mIsFooterEnable);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (viewDataObserver != null) {
                viewDataObserver.onItemRangeInserted(positionStart, itemCount);
            }
            setLoadMoreFinish(mIsFooterEnable);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (viewDataObserver != null) {
                viewDataObserver.onItemRangeRemoved(positionStart, itemCount);
            }
            setLoadMoreFinish(mIsFooterEnable);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (viewDataObserver != null) {
                viewDataObserver.onItemRangeMoved(fromPosition, toPosition, itemCount);
            }
            setLoadMoreFinish(mIsFooterEnable);
        }
    }

    public Adapter getInternalAdapter() {
        return mAutoLoadAdapter != null ? mAutoLoadAdapter.getInternalAdapter() : null;
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        //        if (layout instanceof StaggeredGridLayoutManager) {
        //            ((StaggeredGridLayoutManager) layout).setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //        }
        super.setLayoutManager(layout);
    }

    /**
     * @param resId
     */
    public void addFooterView(int resId) {
        mAutoLoadAdapter.addFooterView(resId);
    }

    public void addFooterView(View headView) {
        mAutoLoadAdapter.addFooterView(headView);
    }

    private void initView() {
        dataObserver = new RecyclerViewDataObserver();
        super.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //                //防止第一行到顶部有空白区域
                //                if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
                //                    ((StaggeredGridLayoutManager) getLayoutManager()).invalidateSpanAssignments();
                //
                //                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    if (!recyclerView.canScrollVertically(-1)) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                mAutoLoadAdapter.notifyDataSetChanged();
                                return;
                            }
                        });
                        return;
                    }
                }
                if (null != mListener && mIsFooterEnable && !mIsLoadingMore && dy > 0) {
                    int lastVisiblePosition = getLastVisiblePosition();
                    if (lastVisiblePosition + 2 == mAutoLoadAdapter.getItemCount()) {
                        //提前显示Loading，只显示，不加载数据
                        mAutoLoadAdapter.showFooterView();
                    }
                    if (lastVisiblePosition + 1 == mAutoLoadAdapter.getItemCount() || isPreLoading && lastVisiblePosition == mAutoLoadAdapter.getItemCount() - 10) {
                        setLoadingMore(true);
                        mLoadMorePosition = lastVisiblePosition;
                        mListener.onLoadMore();
                    }
                }
            }
        });
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    private int getLastVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    int firstPosition = -1;

    /**
     * 获取第一条展示的位置
     *
     * @return
     */
    private int getFirstVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] firstPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMinPosition(firstPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最小的位置
     *
     * @param positions
     * @return
     */
    private int getMinPosition(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int position : positions) {
            minPosition = Math.min(minPosition, position);
        }
        return minPosition;
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int maxPosition = Integer.MIN_VALUE;
        for (int position : positions) {
            maxPosition = Math.max(maxPosition, position);
        }
        return maxPosition;
    }

    /**
     * 设置头部view是否展示
     *
     * @param enable
     */
    public void setHeaderEnable(boolean enable) {
        mAutoLoadAdapter.setHeaderEnable(enable);
    }


    /**
     * 设置是否支持自动加载更多
     *
     * @param autoLoadMore
     */
    public void setAutoLoadMoreEnable(boolean autoLoadMore) {
        mIsFooterEnable = autoLoadMore;
    }

    public class AutoLoadAdapter extends Adapter<ViewHolder> {

        /**
         * 数据adapter
         */
        private Adapter mInternalAdapter;

        private HeaderViewHolder mHeaderViewHolder;
        private FooterViewHolder mFooterViewHolder;

        public AutoLoadAdapter(Adapter adapter) {
            mInternalAdapter = adapter;
            mIsHeaderEnable = false;
        }

        @Override
        public int getItemViewType(int position) {
            int headerPosition = 0;
            int footerPosition = getItemCount() - 1;
            if (headerPosition == position && mIsHeaderEnable && mHeaderViewHolder != null) {
                return TYPE_HEADER;
            } else if (footerPosition == position && mIsFooterEnable) {
                return TYPE_FOOTER;
            } else {
                return mInternalAdapter.getItemViewType(position);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_HEADER:
                    return mHeaderViewHolder;
                case TYPE_FOOTER:
                    if (mFooterViewHolder == null) {
                        mFooterViewHolder = new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_loadmore, parent, false));
                    }
                    return mFooterViewHolder;
                default:
                    return mInternalAdapter.onCreateViewHolder(parent, viewType);

            }
        }

        public class FooterViewHolder extends ViewHolder {
            public View llLoadMore;

            public FooterViewHolder(View itemView) {
                super(itemView);
                llLoadMore = itemView.findViewById(R.id.ll_load_more);
                ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                    params.setFullSpan(true);
                    itemView.setLayoutParams(layoutParams);
                }
                if (isFirstLoadGone) {
                    llLoadMore.setVisibility(GONE);
                } else {
                    llLoadMore.setVisibility(VISIBLE);
                }
            }
        }

        public class HeaderViewHolder extends ViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
                ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                    params.setFullSpan(true);
                    itemView.setLayoutParams(layoutParams);
                }
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type != TYPE_FOOTER && type != TYPE_HEADER) {
                if (mIsHeaderEnable) {
                    --position;
                }
                mInternalAdapter.onBindViewHolder(holder, position);
            }
        }

        public Adapter getInternalAdapter() {
            return mInternalAdapter;
        }

        /**
         * 需要计算上加载更多和添加的头部俩个
         *
         * @return
         */
        @Override
        public int getItemCount() {
            int count = mInternalAdapter.getItemCount();
            if (mIsFooterEnable)
                count++;
            if (mIsHeaderEnable)
                count++;

            return count;
        }

        public void setHeaderEnable(boolean enable) {
            mIsHeaderEnable = enable;
        }

        public void addHeaderView(int resId) {
            mHeaderViewHolder = new HeaderViewHolder(LayoutInflater.from(getContext()).inflate(resId, null));
        }

        public void addHeaderView(View view) {
            mHeaderViewHolder = new HeaderViewHolder(view);
        }

        public void addFooterView(int resId) {
            mFooterViewHolder = new FooterViewHolder(LayoutInflater.from(getContext()).inflate(resId, null));
        }

        public void addFooterView(View view) {
            mFooterViewHolder = new FooterViewHolder(view);
        }

        public void showFooterView() {
            if (mFooterViewHolder != null && mFooterViewHolder.llLoadMore != null/* && mFooterViewHolder.llLoadMore.getVisibility() == GONE*/) {
                mFooterViewHolder.llLoadMore.setVisibility(VISIBLE);
            }
        }

        public void hideFooterView() {
            if (mFooterViewHolder != null && mFooterViewHolder.llLoadMore != null /*&& mFooterViewHolder.llLoadMore.getVisibility() == VISIBLE*/) {
                mFooterViewHolder.llLoadMore.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置正在加载更多
     *
     * @param loadingMore
     */
    public void setLoadingMore(boolean loadingMore) {
        this.mIsLoadingMore = loadingMore;
    }

    /**
     * 设置加载更多的监听
     *
     * @param listener
     */
    public void setOnRecycleViewLoadMoreListener(OnRecycleViewLoadMoreListener listener) {
        mListener = listener;
    }

    /**
     * 加载更多监听
     */
    public interface OnRecycleViewLoadMoreListener {
        /**
         * 加载更多
         */
        void onLoadMore();
    }

    public void setLoadMoreFinish(boolean hasMore) {
        setAutoLoadMoreEnable(hasMore);
        if (mAutoLoadAdapter != null) {
            //            loadMoreFinished = false;
            mAutoLoadAdapter.hideFooterView();
        }
        mIsLoadingMore = false;
    }


    public boolean isIsFooterEnable() {
        return mIsFooterEnable;
    }


    public boolean canViewScrollUp() {
        try {
            if (getAdapter() != null) {
                int count = 0;
                count += (isIsFooterEnable() ? 1 : 0);
                count += ((mIsHeaderEnable) ? 1 : 0);
                if (getAdapter().getItemCount() - count <= 0) {
                    return false;
                }
            }
            final RecyclerView.LayoutManager layoutManager = getLayoutManager();
            firstPosition = 0;
            if (layoutManager instanceof LinearLayoutManager) {
                firstPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            } else if (layoutManager instanceof GridLayoutManager) {
                firstPosition = ((GridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] mFirstVisible = ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(null);
                firstPosition = mFirstVisible[0];
            }
            final int firstPosition = this.firstPosition;// getFirstVisiblePosition();
            return firstPosition != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}