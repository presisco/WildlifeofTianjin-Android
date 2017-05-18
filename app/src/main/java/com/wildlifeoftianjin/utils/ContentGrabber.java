package com.wildlifeoftianjin.utils;

import android.widget.EditText;

/**
 * Created by presisco on 2017/5/17.
 */

public class ContentGrabber {
    public ContentGrabber() {

    }

    public String grabEdit(EditText editText) {
        return editText.getText().toString();
    }
}
