package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public abstract class ListRequest<T> extends JsonArrayRequest {
    private TaskResponse<T> mListener;

    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ListRequest(int method, String url, TaskResponse<T> listener, Response.ErrorListener errorListener) {
        super(method, url, null, null, errorListener);
        mListener = listener;
    }

    /**
     * Returns a list of extra HTTP headers to go along with this request. Can
     * throw {@link AuthFailureError} as authentication may be required to
     * provide these values.
     *
     * @throws AuthFailureError In the event of auth failure
     */
    @Override
    public abstract Map<String, String> getHeaders() throws AuthFailureError;

    protected TaskResponse<T> getTaskResponse() {
        return mListener;
    }

    @Override
    protected abstract void deliverResponse(JSONArray response);
}
