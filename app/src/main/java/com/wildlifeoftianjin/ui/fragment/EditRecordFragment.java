package com.wildlifeoftianjin.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.presisco.lcat.LCAT;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.ui.framework.recyclerview.SelectPictureAdapter;
import com.wildlifeoftianjin.utils.ContentGrabber;
import com.wildlifeoftianjin.utils.ViewFinder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditRecordFragment extends Fragment
        implements SelectPictureAdapter.AddListener {
    private static final int REQUEST_CODE_PICK_PICTURE = 10;

    private String creature_name = "";

    private EditText mCountEdit;
    private EditText mTimeEdit;
    private EditText mSpecieEdit;
    private EditText mLocationEdit;
    private EditText mDescriptionEdit;

    private SelectPictureAdapter mPictureAdapter;

    public EditRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditRecordFragment.
     */
    public static EditRecordFragment newInstance() {
        EditRecordFragment fragment = new EditRecordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_record, container, false);
        ViewFinder finder = new ViewFinder(rootView);

        mCountEdit = finder.findEdit(R.id.editCount);
        mTimeEdit = finder.findEdit(R.id.editTime);
        mDescriptionEdit = finder.findEdit(R.id.editDescription);
        mLocationEdit = finder.findEdit(R.id.editLocation);
        mSpecieEdit = finder.findEdit(R.id.editSpecie);

        finder.findToggleButton(R.id.toggleLock).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        mSpecieEdit.setText(creature_name);
        mTimeEdit.setText(
                Record.DATE_TIME_FORMAT.format(System.currentTimeMillis()));

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listImage);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mPictureAdapter = new SelectPictureAdapter(getContext(), this);
        recyclerView.setAdapter(mPictureAdapter);

        return rootView;
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();
        LCAT.d(this, "onStop");
    }

    @Override
    public void onAddPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_PICK_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                mPictureAdapter.addPicture(data.getData());
            }
        }
    }

    public void setCreatureName(String name) {
        if (mSpecieEdit != null) {
            mSpecieEdit.setText(name);
        } else {
            creature_name = name;
        }
    }

    public Record getRecord() {
        ContentGrabber cb = new ContentGrabber();
        Record record = new Record();
        record.classification = cb.grabEdit(mSpecieEdit);
        record.location = cb.grabEdit(mLocationEdit);
        record.count = Integer.parseInt(cb.grabEdit(mCountEdit));
        record.description = cb.grabEdit(mDescriptionEdit);
        record.time = cb.grabEdit(mTimeEdit);
        return record;
    }

    public List<Uri> getPictures() {
        return mPictureAdapter.getPictures();
    }
}
