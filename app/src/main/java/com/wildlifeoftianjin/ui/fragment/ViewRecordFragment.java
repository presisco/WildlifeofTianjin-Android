package com.wildlifeoftianjin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.utils.ViewFinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRecordFragment extends Fragment {

    private TextView mCountText;
    private TextView mTimeText;
    private TextView mSpecieText;
    private TextView mLocationText;
    private TextView mDescriptionText;
    private TextView mUserText;

    public ViewRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditRecordFragment.
     */
    public static ViewRecordFragment newInstance() {
        ViewRecordFragment fragment = new ViewRecordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_record, container, false);
        ViewFinder finder = new ViewFinder(rootView);

        mCountText = finder.findText(R.id.textCount);
        mTimeText = finder.findText(R.id.textTime);
        mSpecieText = finder.findText(R.id.textSpecie);
        mDescriptionText = finder.findText(R.id.textDescription);
        mLocationText = finder.findText(R.id.textLocation);
        mUserText = finder.findText(R.id.textUser);

        return rootView;
    }

    public void setRecord(Record record) {
        mCountText.setText(record.count);
        mTimeText.setText(record.time);
        mSpecieText.setText(record.classification);
        mDescriptionText.setText(record.description);
        mLocationText.setText(record.location);
        mUserText.setText(record.username);
    }
}
