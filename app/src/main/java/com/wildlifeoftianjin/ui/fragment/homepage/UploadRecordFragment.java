package com.wildlifeoftianjin.ui.fragment.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.presisco.lcat.LCAT;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.ui.fragment.EditRecordFragment;
import com.wildlifeoftianjin.ui.fragment.NetworkFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link UploadRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UploadRecordFragment extends NetworkFragment {

    private EditRecordFragment mEditRecordFragment;

    public UploadRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static UploadRecordFragment newInstance() {
        UploadRecordFragment fragment = new UploadRecordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditRecordFragment = EditRecordFragment.newInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_upload_record, container, false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.recordFragment, mEditRecordFragment);
        transaction.commit();

        rootView.findViewById(R.id.buttonUpload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpload(v);
            }
        });

        rootView.findViewById(R.id.imageSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave(v);
            }
        });

        return rootView;
    }

    public void onUpload(View v) {
        LCAT.d(this, "onUpload");
    }

    public void onSave(View v) {
        LCAT.d(this, "onSave");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
