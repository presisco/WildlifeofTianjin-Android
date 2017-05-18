package com.wildlifeoftianjin.ui.framework.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.toolbox.ImageLoader;
import com.presisco.lcat.LCAT;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.network.NetQueueSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by presisco on 2017/5/16.
 */

public class DragAppendAdapter<CONTENT> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected static final int VIEW_TYPE_FOOTER = 0;
    protected static final int VIEW_TYPE_CONTENT = 1;
    private boolean show_footer = true;
    private List<CONTENT> mDataSet = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ActionListener mActionListener;
    private FooterListener mFooterListener;
    private NetQueueSingleton mNetQueue;

    public DragAppendAdapter(Context context) {
        mContext = context;
        mNetQueue = NetQueueSingleton.getInstance(mContext);
    }

    public DragAppendAdapter(Context context, ActionListener listener, FooterListener footerListener) {
        this(context);
        mLayoutInflater = LayoutInflater.from(context);
        mActionListener = listener;
        mFooterListener = footerListener;
    }

    public void setActionListener(ActionListener listener) {
        mActionListener = listener;
    }

    public void setFooterListener(FooterListener listener) {
        mFooterListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FOOTER) {
            return new FooterHolder(getLayoutInflater().inflate(R.layout.listitem_footer, parent, false));
        } else {
            return mActionListener.onCreate(getLayoutInflater(), parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentHolder) {
            mActionListener.onBind((ContentHolder) holder, position);
        } else {
            mFooterListener.onShowingFooter();
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof ContentHolder) {
            LCAT.d(this, "recycled", "content");
            mActionListener.onRecycled((ContentHolder) holder);
        } else {
            LCAT.d(this, "recycled", "footer");
        }
    }

    public void setDataSet(List<CONTENT> dataset) {
        mDataSet = dataset;
    }

    public void appendDataSet(List<CONTENT> dataset) {
        mDataSet.addAll(dataset);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mDataSet.size())
            return VIEW_TYPE_CONTENT;
        else
            return VIEW_TYPE_FOOTER;
    }

    @Override
    public int getItemCount() {
        if (show_footer)
            return mDataSet.size() + 1;
        else
            return mDataSet.size();
    }

    public void setShowingFooter(boolean flag) {
        show_footer = flag;
    }

    public CONTENT getItem(int position) {
        return mDataSet.get(position);
    }

    protected ImageLoader getImageLoader() {
        return mNetQueue.getImageLoader();
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    protected Context getContext() {
        return mContext;
    }

    public interface ActionListener {
        ContentHolder onCreate(LayoutInflater inflater, ViewGroup parent);

        void onBind(ContentHolder holder, int position);

        void onRecycled(ContentHolder holder);
    }

    public interface FooterListener {
        void onShowingFooter();
    }

    protected static abstract class ContentHolder extends RecyclerView.ViewHolder {
        public ContentHolder(View itemView) {
            super(itemView);
        }
    }

    protected static class FooterHolder extends RecyclerView.ViewHolder {
        public final ProgressBar mProgress;

        public FooterHolder(View itemView) {
            super(itemView);
            mProgress = (ProgressBar) itemView.findViewById(R.id.listLoadingProgress);
        }
    }
}
