package com.wildlifeoftianjin.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildlifeoftianjin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link UploadRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UploadRecordFragment extends Fragment {

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
        mEditRecordFragment = EditRecordFragment.newInstance("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_record, container, false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.recordFragment, mEditRecordFragment);
        transaction.commit();

        return rootView;
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
