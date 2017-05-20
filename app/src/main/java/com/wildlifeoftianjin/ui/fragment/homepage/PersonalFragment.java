package com.wildlifeoftianjin.ui.fragment.homepage;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.ui.activity.LoginActivity;
import com.wildlifeoftianjin.ui.activity.RecordListActivity;
import com.wildlifeoftianjin.ui.activity.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {
    private static final int REQUEST_CODE_SIGN_IN = 6666;

    private SharedPreferences mPreferences;
    private TextView mUsernameText;
    private Resources mRes;

    private String user_id;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mRes = getContext().getResources();
        user_id = mPreferences.getString("user_id", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);

        rootView.findViewById(R.id.imageSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        rootView.findViewById(R.id.userLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), REQUEST_CODE_SIGN_IN);
            }
        });

        rootView.findViewById(R.id.layoutAllRecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RecordListActivity.class)
                        .putExtra(RecordListActivity.Companion.getKEY_REQUEST_MODE(), RecordListActivity.Companion.getMODE_USER())
                        .putExtra(RecordListActivity.Companion.getKEY_USER_ID(), user_id));
            }
        });

        mUsernameText = (TextView) rootView.findViewById(R.id.textUsername);
        mUsernameText.setText(mPreferences.getString("user_name", mRes.getString(R.string.label_click_login)));
        return rootView;
    }

    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * {@link Activity#onActivityResult(int, int, Intent)}.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                mUsernameText.setText(mPreferences.getString("user_name", mRes.getString(R.string.label_click_login)));
            }
        }
    }
}
