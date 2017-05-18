package com.wildlifeoftianjin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.RecordOverview;
import com.wildlifeoftianjin.network.NetQueueSingleton;
import com.wildlifeoftianjin.network.Request.ListRequest;
import com.wildlifeoftianjin.network.Request.RecordListRequest;
import com.wildlifeoftianjin.ui.framework.recyclerview.DragAppendAdapter;
import com.wildlifeoftianjin.ui.framework.recyclerview.RecordListAdapter;

import java.util.List;

public class RecordListActivity extends AppCompatActivity
        implements RecordListAdapter.RecordListener,
        DragAppendAdapter.FooterListener,
        ListRequest.ListResponse<RecordOverview>,
        Response.ErrorListener {
    public static final String KEY_CREATURE_ID = "creature_id";

    private String creature_id;

    private RequestQueue mRequestQueue;

    private RecyclerView mRecordList;
    private RecordListAdapter mRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        creature_id = getIntent().getStringExtra(KEY_CREATURE_ID);
        mRequestQueue = NetQueueSingleton.getInstance(this).getRequestQueue();

        mRecordList = (RecyclerView) findViewById(R.id.listRecord);
        mRecordAdapter = new RecordListAdapter(this, this, this);
        mRecordList.setAdapter(mRecordAdapter);

        mRequestQueue.add(new RecordListRequest(creature_id, this, this));
    }

    @Override
    public void onClickRecord(int position) {
        startActivity(
                new Intent(this, ViewRecordActivity.class)
                        .putExtra(
                                ViewRecordActivity.KEY_RECORD_ID,
                                mRecordAdapter.getItem(position)
                                        .getRecordID()));
    }

    @Override
    public void onShowingFooter() {
        mRequestQueue.add(new RecordListRequest(creature_id, this, this));
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(List<RecordOverview> response) {
        mRecordAdapter.appendDataSet(response);
        mRecordAdapter.notifyDataSetChanged();
    }
}
