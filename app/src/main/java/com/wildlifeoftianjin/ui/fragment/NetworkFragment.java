package com.wildlifeoftianjin.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.presisco.lcat.LCAT;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.network.NetQueueSingleton;

/**
 * Created by presisco on 2017/5/18.
 */

public class NetworkFragment extends Fragment implements Response.ErrorListener {
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private ProgressDialog mProgress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = NetQueueSingleton.getInstance(getContext()).getRequestQueue();
        mImageLoader = NetQueueSingleton.getInstance(getContext()).getImageLoader();
        mProgress = new ProgressDialog(getContext());
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setIndeterminate(true);
        mProgress.setTitle(getContext().getResources().getString(R.string.label_loading));
    }

    protected void showLoadingIndicator() {
        mProgress.show();
    }

    protected void hideLoadingIndicator() {
        mProgress.hide();
    }

    protected RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    protected ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        LCAT.e(this, "network", error.toString());
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
        hideLoadingIndicator();
    }
}
