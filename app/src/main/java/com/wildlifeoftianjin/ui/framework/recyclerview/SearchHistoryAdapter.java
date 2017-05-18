package com.wildlifeoftianjin.ui.framework.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.wildlifeoftianjin.R;

/**
 * Created by presisco on 2017/5/17.
 */

public class SearchHistoryAdapter extends CursorAdapter {
    public SearchHistoryAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.listitem_search_history, parent, false);
        ((TextView) rootView.findViewById(R.id.textContent)).setText(cursor.getString(1));
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.textContent)).setText(cursor.getString(1));
    }
}
