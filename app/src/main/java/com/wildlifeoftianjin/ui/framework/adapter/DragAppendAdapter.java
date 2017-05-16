package com.wildlifeoftianjin.ui.framework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by presisco on 2017/5/16.
 */

public class DragAppendAdapter<CONTENT> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean show_footer = true;
    private List<CONTENT> mDataSet;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void setDataSet(List<CONTENT> dataset) {
        mDataSet = dataset;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (show_footer)
            return mDataSet.size() + 1;
        else
            return mDataSet.size();
    }

    protected static abstract class ContentHolder extends RecyclerView.ViewHolder {
        public ContentHolder(View itemView) {
            super(itemView);
        }
    }

    protected static abstract class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
