package com.wildlifeoftianjin.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by presisco on 2017/5/17.
 */

public class ViewFinder {
    private View rootView;

    public ViewFinder(View root) {
        rootView = root;
    }

    public TextView findText(int id) {
        return (TextView) rootView.findViewById(id);
    }

    public EditText findEdit(int id) {
        return (EditText) rootView.findViewById(id);
    }

    public ToggleButton findToggleButton(int id) {
        return (ToggleButton) rootView.findViewById(id);
    }
}
