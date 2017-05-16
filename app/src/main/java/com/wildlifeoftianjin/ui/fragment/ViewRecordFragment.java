package com.wildlifeoftianjin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildlifeoftianjin.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRecordFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";

    private String mName = "";

    private HashMap<String, TextView> textMap = new HashMap<>();

    private TextView mCountText;
    private TextView mTimeText;
    private TextView mSpecieText;
    private TextView mLocationText;
    private TextView mDescriptionText;

    public ViewRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter mode.
     * @return A new instance of fragment EditRecordFragment.
     */
    public static ViewRecordFragment newInstance(String name) {
        ViewRecordFragment fragment = new ViewRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_record, container, false);

        mCountText = (TextView) rootView.findViewById(R.id.textCount);
        mTimeText = (TextView) rootView.findViewById(R.id.textTime);

        return rootView;
    }

    public void setRecord() {

    }
}
