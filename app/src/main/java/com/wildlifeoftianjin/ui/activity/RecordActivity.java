package com.wildlifeoftianjin.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.ui.fragment.ViewRecordFragment;

public class RecordActivity extends AppCompatActivity {
    private ViewRecordFragment mEditRecordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        mEditRecordFragment = ViewRecordFragment.newInstance("");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.recordFragment, mEditRecordFragment);
        transaction.commit();
    }
}
