package com.wildlifeoftianjin.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.wildlifeoftianjin.network.NetQueueSingleton;

/**
 * Created by presisco on 2017/5/18.
 */

public abstract class NetworkActivity extends AppCompatActivity implements Response.ErrorListener {
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = NetQueueSingleton.getInstance(this).getRequestQueue();
        mImageLoader = NetQueueSingleton.getInstance(this).getImageLoader();
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
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }
}
