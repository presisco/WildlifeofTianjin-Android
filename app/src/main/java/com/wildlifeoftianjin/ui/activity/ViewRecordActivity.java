package com.wildlifeoftianjin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.volley.VolleyError;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.network.Request.ObjectRequest;
import com.wildlifeoftianjin.network.Request.RecordRequest;
import com.wildlifeoftianjin.ui.fragment.ViewRecordFragment;

public class ViewRecordActivity extends NetworkActivity
        implements ObjectRequest.ObjectResponse<Record> {
    public static final String KEY_RECORD_ID = "record_id";

    private ViewRecordFragment mViewRecordFragment;

    private String creature_name = "";
    private String creature_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        mViewRecordFragment = ViewRecordFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.record_fragment, mViewRecordFragment);
        transaction.commit();

        getRequestQueue().add(
                new RecordRequest(getIntent().getStringExtra(KEY_RECORD_ID), this, this));
        showLoadingIndicator();
    }

    public void onBack(View v) {
        finish();
    }

    public void onShare(View v) {

    }

    public void onEdit(View v) {
        startActivity(
                new Intent(
                        this, EditRecordActivity.class)
                        .putExtra(EditRecordActivity.Companion.getKEY_CREATURE_NAME(), creature_name)
                        .putExtra(EditRecordActivity.Companion.getKEY_CREATURE_ID(), creature_id));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }

    @Override
    public void onResponse(Record response) {
        creature_name = response.scientificName;
        creature_id = response.creatureID;
        mViewRecordFragment.setRecord(response);
        hideLoadingIndicator();
    }
}
