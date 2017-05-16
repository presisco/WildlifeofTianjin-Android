package com.wildlifeoftianjin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.Record;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditRecordFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";

    private String mName = "";

    private EditText mCountEdit;

    public EditRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter mode.
     * @return A new instance of fragment EditRecordFragment.
     */
    public static EditRecordFragment newInstance(String name) {
        EditRecordFragment fragment = new EditRecordFragment();
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

        return rootView;
    }

    public Record getRecord() {
        return null;
    }
}
