package com.androidbootstrap.ui.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/17
 * @since 1.0
 */
public abstract class BaseListAdapter<ENTITY, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<ENTITY> data = new ArrayList<>();

    public final void setData(List<ENTITY> data) {
        this.data = data;
    }

    public final void addData(List<ENTITY> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
        }
    }

    public List<ENTITY> getData() {
        return data;
    }
}
