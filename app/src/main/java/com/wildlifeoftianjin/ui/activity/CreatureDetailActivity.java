package com.wildlifeoftianjin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.Creature;
import com.wildlifeoftianjin.network.Request.ObjectRequest;

public class CreatureDetailActivity extends NetworkActivity
        implements ObjectRequest.ObjectResponse<Creature> {
    public static final String KEY_CREATURE_ID = "creature_id";

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

    protected TextView findText(int id) {
        return (TextView) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_detail);
        creature_id = getIntent().getStringExtra(KEY_CREATURE_ID);

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

        mPhotoPager = (ViewPager) findViewById(R.id.pagerPhoto);
    }

    @Override
    public void onResponse(Creature response) {
        mCreatureNameText.setText(response.scientificName);
        mEnglishNameText.setText(response.englishName);
        mLatinNameText.setText(response.latinName);
        mNickNameText.setText(response.nickName);
        mClassificationText.setText(response.classification);
        mSummaryText.setText(response.summary);
        mFeatureText.setText(response.feature);
        mHabitText.setText(response.habit);
        mDistributionText.setText(response.distribution);
        mValueText.setText(response.value);
        mOtherText.setText(response.other);
    }

    public void onBack(View v) {
        finish();
    }

    public void onEdit(View v) {
        startActivity(new Intent(this, EditRecordActivity.class).putExtra(EditRecordActivity.KEY_CREATURE_NAME, ""));
    }

    public void onShare(View v) {

    }

    public void onRecord(View v) {
        startActivity(new Intent(this, RecordListActivity.class).putExtra(RecordListActivity.KEY_CREATURE_ID, creature_id));
    }

}
