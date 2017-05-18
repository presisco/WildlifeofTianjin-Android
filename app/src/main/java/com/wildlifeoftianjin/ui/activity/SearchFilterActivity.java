package com.wildlifeoftianjin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wildlifeoftianjin.R;

public class SearchFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_search_filter);
    }

    public void onApply(View v) {
        finish();
    }

    public void onCancel(View v) {
        finish();
    }
}
