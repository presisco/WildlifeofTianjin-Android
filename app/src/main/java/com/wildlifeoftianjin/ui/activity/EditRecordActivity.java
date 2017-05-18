package com.wildlifeoftianjin.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.data.NetResult;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.network.Request.UploadRecordRequest;
import com.wildlifeoftianjin.network.Request.UploadRequest;
import com.wildlifeoftianjin.ui.fragment.EditRecordFragment;

/**
 * Created by presisco on 2017/5/18.
 */

public class EditRecordActivity extends NetworkActivity
        implements UploadRequest.UploadResponse {
    public static final String KEY_CREATURE_NAME = "name";

    private EditRecordFragment mEditRecordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        mEditRecordFragment = new EditRecordFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.recordFragment, mEditRecordFragment);
        transaction.commit();

        String creature_name = getIntent().getStringExtra(KEY_CREATURE_NAME);
        mEditRecordFragment.setCreatureName(creature_name);
    }

    public void onUpload(View v) {
        Record record = mEditRecordFragment.getRecord();
        getRequestQueue().add(new UploadRecordRequest(record, this, this));
    }

    @Override
    public void onResponse(NetResult result) {

    }
}
