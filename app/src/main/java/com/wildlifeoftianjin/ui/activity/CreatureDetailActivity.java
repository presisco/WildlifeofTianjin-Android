package com.wildlifeoftianjin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.Creature;
import com.wildlifeoftianjin.network.Request.CreatureRequest;
import com.wildlifeoftianjin.network.Request.ObjectRequest;
import com.wildlifeoftianjin.ui.framework.viewpager.WebImageAdapter;

public class CreatureDetailActivity extends NetworkActivity
        implements ObjectRequest.ObjectResponse<Creature> {
    public static final String KEY_CREATURE_ID = "creature_id";

    private String creature_name;
    private String creature_id;

    private ViewPager mPhotoPager;
    private TextView mCreatureNameText;
    private TextView mEnglishNameText;
    private TextView mLatinNameText;
    private TextView mNickNameText;
    private TextView mClassificationText;
    private TextView mSummaryText;
    private TextView mFeatureText;
    private TextView mHabitText;
    private TextView mDistributionText;
    private TextView mValueText;
    private TextView mOtherText;
    private TextView mProtectionLevelText;

    private WebImageAdapter mAdapter;

    protected TextView findText(int id) {
        return (TextView) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_detail);
        creature_id = Integer.toString(getIntent().getIntExtra(KEY_CREATURE_ID, 0));

        mCreatureNameText = findText(R.id.textCreatureName);
        mEnglishNameText = findText(R.id.textEnglishName);
        mLatinNameText = findText(R.id.textLatinName);
        mNickNameText = findText(R.id.textNickName);
        mClassificationText = findText(R.id.textClassification);
        mSummaryText = findText(R.id.textSummary);
        mFeatureText = findText(R.id.textFeature);
        mHabitText = findText(R.id.textHabit);
        mDistributionText = findText(R.id.textDistribution);
        mValueText = findText(R.id.textValue);
        mOtherText = findText(R.id.textOther);
        mProtectionLevelText = findText(R.id.textProtectionLevel);

        mAdapter = new WebImageAdapter(this);
        mPhotoPager = (ViewPager) findViewById(R.id.pagerPhoto);
        mPhotoPager.setAdapter(mAdapter);

        getRequestQueue().add(new CreatureRequest(creature_id, this, this));
        showLoadingIndicator();
    }

    @Override
    public void onResponse(Creature response) {
        mCreatureNameText.setText(response.scientificName);
        creature_name = response.scientificName;
        mEnglishNameText.setText(response.englishName);
        mLatinNameText.setText(response.latinName);
        mNickNameText.setText(response.nickName);
        mClassificationText.setText(response.classification);
        mSummaryText.setText(response.summary);
        mFeatureText.setText(response.feature);
        mHabitText.setText(response.habit);
        mProtectionLevelText.setText(response.protectionLevel);
        mDistributionText.setText(response.distribution);
        mValueText.setText(response.value);
        mOtherText.setText(response.other);
        mAdapter.setImageUrls(response.images);
        mAdapter.notifyDataSetChanged();
        hideLoadingIndicator();
    }

    public void onBack(View v) {
        finish();
    }

    public void onEdit(View v) {
        startActivity(
                new Intent(this, EditRecordActivity.class)
                        .putExtra(EditRecordActivity.Companion.getKEY_CREATURE_NAME(), creature_name)
                        .putExtra(EditRecordActivity.Companion.getKEY_CREATURE_ID(), creature_id));
    }

    public void onShare(View v) {

    }

    public void onRecord(View v) {
        startActivity(
                new Intent(this, RecordListActivity.class)
                        .putExtra(RecordListActivity.Companion.getKEY_REQUEST_MODE(), RecordListActivity.Companion.getMODE_CREATURE())
                        .putExtra(RecordListActivity.Companion.getKEY_CREATURE_ID(), creature_id)
                        .putExtra(RecordListActivity.Companion.getKEY_CREATURE_NAME(), creature_name));
    }

}
