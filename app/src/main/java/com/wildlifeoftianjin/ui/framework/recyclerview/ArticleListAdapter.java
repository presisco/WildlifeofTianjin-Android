package com.wildlifeoftianjin.ui.framework.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wildlifeoftianjin.model.ArticleOverview;

/**
 * Created by presisco on 2017/5/16.
 */

public class ArticleListAdapter extends DragAppendAdapter<ArticleOverview> {
    public ArticleListAdapter(Context context) {
        super(context);
    }

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
}
