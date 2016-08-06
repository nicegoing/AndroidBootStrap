package com.library.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * RecycleView Item Margin
 * item 从0索引开始设置item间距及item与recycleView的边距
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int cardElevation = 0;
    /**
     * item 间距
     */
    private int mItemOffset;
    /**
     * 设置需要铺满全屏的item ，从0索引开始的选项有多少个是否需要要全屏
     */
    private boolean[] itemViewFull = null;
    /**
     * 是否所有item铺满全屏
     */
    private boolean isItemViewFullAll = false;
    /**
     * 列表方向{@link OrientationHelper#VERTICAL}或{@link OrientationHelper#HORIZONTAL}
     */
    private int orientation = OrientationHelper.VERTICAL;
    /**
     * 是否设置第一个item与顶部间距
     */
    private boolean isOneItemViewSpaceFull = false;
    /**
     * 第二个item与第一个item之间无间距
     */
    private boolean isSecondItemViewSpaceFull = true;

    /**
     * 初始化recycle view item间距
     *
     * @param itemOffset item间距
     * @author dingpeihua
     * @date 2016/3/25 9:46
     * @version 1.0
     */
    public ItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    /**
     * 初始化recycle view部分item宽度全屏
     *
     * @param itemOffset   item间距
     * @param itemViewFull item是否全屏 数组类型，从recycle view position 为0开始
     * @author dingpeihua
     * @date 2016/3/25 9:43
     * @version 1.0
     */
    public ItemOffsetDecoration(int itemOffset, boolean... itemViewFull) {
        this(itemOffset, 0, itemViewFull);
    }

    /**
     * 初始化recycle view部分item宽度全屏
     *
     * @param itemOffset    item间距
     * @param itemViewFull  item是否全屏 数组类型，从recycle view position 为0开始
     * @param cardElevation cardView 阴影高度
     * @author dingpeihua
     * @date 2016/3/25 9:43
     * @version 1.0
     */
    public ItemOffsetDecoration(int itemOffset, int cardElevation, boolean... itemViewFull) {
        mItemOffset = itemOffset;
        this.itemViewFull = itemViewFull;
        this.cardElevation = cardElevation;
        this.isItemViewFullAll = false;
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#VERTICAL}表示所有item宽度充满全屏<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#HORIZONTAL}表示所有item高度充满全屏<br>
     *
     * @param isItemViewFullAll 是否所有item全屏
     * @param itemOffset        item间距
     * @author dingpeihua
     * @date 2016/3/17 17:22
     * @version 1.0
     */
    public ItemOffsetDecoration(boolean isItemViewFullAll, int itemOffset) {
        this(isItemViewFullAll, 0, itemOffset);
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#VERTICAL}表示所有item宽度充满全屏<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#HORIZONTAL}表示所有item高度充满全屏<br>
     *
     * @param isItemViewFullAll 是否所有item全屏
     * @param itemOffset        item间距
     * @param cardElevation     cardView 阴影高度
     * @author dingpeihua
     * @date 2016/3/17 17:22
     * @version 1.0
     */
    public ItemOffsetDecoration(boolean isItemViewFullAll, int cardElevation, int itemOffset) {
        this(itemOffset);
        this.isItemViewFullAll = isItemViewFullAll;
        this.cardElevation = cardElevation;
        this.itemViewFull = null;
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#VERTICAL}表示所有item宽度充满全屏<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#HORIZONTAL}表示所有item高度充满全屏<br>
     *
     * @param isItemViewFullAll  是否所有item全屏
     * @param isOneItemViewSpace 是否设置第一个item与顶部间距
     * @param cardElevation      cardView 阴影高度
     * @param itemOffset         item间距
     * @author dingpeihua
     * @date 2016/3/17 17:22
     * @version 1.0
     */
    public ItemOffsetDecoration(boolean isItemViewFullAll, boolean isOneItemViewSpace, int cardElevation, int itemOffset) {
        this(isItemViewFullAll, cardElevation, itemOffset);
        this.isOneItemViewSpaceFull = isOneItemViewSpace;
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#VERTICAL}表示所有item宽度充满全屏<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#HORIZONTAL}表示所有item高度充满全屏<br>
     *
     * @param isItemViewFullAll         是否所有item全屏
     * @param itemOffset                item间距
     * @param isSecondItemViewSpaceFull 第二个item与第一个item之间无间距
     * @author dingpeihua
     * @date 2016/6/7 09:32
     * @version 1.0
     */
    public ItemOffsetDecoration(boolean isItemViewFullAll, int itemOffset, int cardElevation, boolean isSecondItemViewSpaceFull) {
        this(isItemViewFullAll, cardElevation, itemOffset);
        this.isSecondItemViewSpaceFull = isSecondItemViewSpaceFull;
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#VERTICAL}表示所有item宽度充满全屏<br>
     * 如果{@link #isItemViewFullAll}为true并且{@link #orientation}等于{@link OrientationHelper#HORIZONTAL}表示所有item高度充满全屏<br>
     *
     * @param isItemViewFullAll  是否所有item全屏
     * @param isOneItemViewSpace 是否设置第一个item与顶部间距
     * @param itemOffset         item间距
     * @author dingpeihua
     * @date 2016/3/17 17:22
     * @version 1.0
     */
    public ItemOffsetDecoration(boolean isItemViewFullAll, boolean isOneItemViewSpace, int itemOffset) {
        this(isItemViewFullAll, 0, itemOffset);
        this.isOneItemViewSpaceFull = isOneItemViewSpace;
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     *
     * @param context
     * @param isItemViewFullAll 是否所有item全屏
     * @param itemOffsetId      item间距 DimenRes id
     * @author dingpeihua
     * @date 2016/3/25 10:01
     * @version 1.0
     */
    public ItemOffsetDecoration(@NonNull Context context, boolean isItemViewFullAll, int cardElevation, @DimenRes int itemOffsetId) {
        this(isItemViewFullAll, cardElevation, context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    /**
     * 初始化recyclerView所有item宽度全屏，<br>
     *
     * @param context
     * @param isItemViewFullAll  是否所有item全屏
     * @param isOneItemViewSpace 是否设置第一个item与顶部间距
     * @param itemOffsetId       item间距 DimenRes id
     * @author dingpeihua
     * @date 2016/3/25 10:01
     * @version 1.0
     */
    public ItemOffsetDecoration(@NonNull Context context, boolean isItemViewFullAll, boolean isOneItemViewSpace, @DimenRes int cardElevationRes, @DimenRes int itemOffsetId) {
        this(isItemViewFullAll, isOneItemViewSpace, context.getResources().getDimensionPixelSize(cardElevationRes), context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
        itemViewFull = null;
        this.isItemViewFullAll = false;
        this.isOneItemViewSpaceFull = false;
    }

    /**
     * 初始化recycle view部分item宽度全屏
     *
     * @param itemOffsetId item间距 DimenRes id
     * @param itemViewFull item是否全屏 数组类型，从recycle view position 为0开始
     * @author dingpeihua
     * @date 2016/3/25 9:43
     * @version 1.0
     */
    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId, boolean... itemViewFull) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId), itemViewFull);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (itemViewFull != null && itemViewFull.length > 0) {
            final int itemPosition = parent.getChildAdapterPosition(view);
            if (itemPosition < itemViewFull.length) {
                for (int i = 0; i < itemViewFull.length; i++) {
                    if (i == itemPosition && itemViewFull[i]) {
                        outRect.set(-cardElevation, 0, -cardElevation, 0);
                        return;
                    }
                }
            }
        } else if (isItemViewFullAll) {
            if (layoutManager instanceof LinearLayoutManager) {
                orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            } else if (layoutManager instanceof GridLayoutManager) {
                orientation = ((GridLayoutManager) layoutManager).getOrientation();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
            final int itemPosition = parent.getChildAdapterPosition(view);
            if (orientation == OrientationHelper.VERTICAL) {
                if (itemPosition == 0) {
                    outRect.set(-cardElevation, isOneItemViewSpaceFull ? mItemOffset : -cardElevation, -cardElevation, mItemOffset);
                } else if (itemPosition == 1) {
                    outRect.set(-cardElevation, isSecondItemViewSpaceFull ? mItemOffset : -cardElevation - mItemOffset, -cardElevation, mItemOffset);
                } else {
                    if (setBottomDecoration(parent, itemPosition, outRect, orientation)) {
                        return;
                    }
                    outRect.set(-cardElevation, mItemOffset, -cardElevation, mItemOffset);
                }
                return;
            } else if (orientation == OrientationHelper.HORIZONTAL) {
                if (itemPosition == 0) {
                    outRect.set(isOneItemViewSpaceFull ? mItemOffset: -cardElevation, -cardElevation, mItemOffset, -cardElevation);
                } else if (itemPosition == 1) {
                    outRect.set(-cardElevation, isSecondItemViewSpaceFull ? mItemOffset : -cardElevation - mItemOffset, -cardElevation, mItemOffset);
                } else {
                    if (setBottomDecoration(parent, itemPosition, outRect, orientation)) {
                        return;
                    }
                    outRect.set(mItemOffset, -cardElevation, mItemOffset, -cardElevation);
                }
                return;
            }
        }
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }

    /**
     * 设置RecyclerView最后一个item底部无间距
     *
     * @param parent       RecyclerView 对象
     * @param itemPosition item索引
     * @param outRect      Rect 接收输出
     * @param orientation  列表方向水平{@link OrientationHelper#HORIZONTAL} 或垂直{@link OrientationHelper#VERTICAL}
     * @return 如果成功设置返回true，反之false
     * @author dingpeihua
     * @date 2016/5/11 17:10
     * @version 1.0
     */
    private boolean setBottomDecoration(RecyclerView parent, int itemPosition, Rect outRect, int orientation) {
        final RecyclerView.Adapter adapter;
        if (parent instanceof SmartRecyclerView) {
            final SmartRecyclerView recyclerView = (SmartRecyclerView) parent;
            adapter = recyclerView.getInternalAdapter();
        } else {
            adapter = parent.getAdapter();
        }
        final int itemCount = adapter != null ? adapter.getItemCount() - 1 : 0;
        if (itemPosition == itemCount) {//最后一个item不设置底部间距
            final boolean isHorizontal = orientation == OrientationHelper.HORIZONTAL;
            outRect.set(isHorizontal ? mItemOffset : -cardElevation, isHorizontal ? -cardElevation : mItemOffset, -cardElevation, -cardElevation);
            return true;
        }
        return false;
    }

}