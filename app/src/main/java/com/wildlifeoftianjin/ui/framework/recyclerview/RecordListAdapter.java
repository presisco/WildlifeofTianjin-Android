package com.wildlifeoftianjin.ui.framework.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.RecordOverview;

/**
 * Created by presisco on 2017/5/17.
 */

public class RecordListAdapter extends DragAppendAdapter<RecordOverview> implements DragAppendAdapter.ActionListener {
    private RecordListener mListener;

    public RecordListAdapter(Context context, FooterListener listener, RecordListener recordListener) {
        super(context);
        setActionListener(this);
        setFooterListener(listener);
        mListener = recordListener;
    }

    @Override
    public ContentHolder onCreate(LayoutInflater inflater, ViewGroup parent) {
        return new RecordHolder(inflater.inflate(R.layout.listitem_record_overview, parent, false), mListener);
    }

    @Override
    public void onBind(ContentHolder holder, int position) {
        RecordHolder recordHolder = (RecordHolder) holder;
        RecordOverview overview = getItem(position);
        recordHolder.timeText.setText(overview.getTime());
        recordHolder.locationText.setText(overview.getLocation());
    }

    @Override
    public void onRecycled(ContentHolder holder) {

    }

    public interface RecordListener {
        void onClickRecord(int position);
    }

    protected static class RecordHolder extends ContentHolder {
        public final TextView timeText;
        public final TextView locationText;
        public final TextView countText;

        public RecordHolder(View itemView, final RecordListener listener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickRecord(getAdapterPosition());
                }
            });
            timeText = (TextView) itemView.findViewById(R.id.textTime);
            locationText = (TextView) itemView.findViewById(R.id.textLocation);
            countText = (TextView) itemView.findViewById(R.id.textCount);
        }
    }
}
