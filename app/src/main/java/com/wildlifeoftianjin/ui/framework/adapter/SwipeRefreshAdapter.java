package com.wildlifeoftianjin.ui.framework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by presisco on 2017/5/16.
 */

public class SwipeRefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected static abstract class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
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
