package com.androidbootstrap.ui.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidbootstrap.R;
import com.androidbootstrap.bean.Name;
import com.androidbootstrap.ui.base.BaseListAdapter;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public class TestListAdapter extends BaseListAdapter<Name,TestListAdapter.MyViewHolder> {
    Activity content;



    public TestListAdapter(Activity activity) {
        this.content = activity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(content).inflate(R.layout.item_test_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position).name().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
