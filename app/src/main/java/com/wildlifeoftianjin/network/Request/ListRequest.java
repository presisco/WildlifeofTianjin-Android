package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by presisco on 2017/5/16.
 */

public abstract class ListRequest<T> extends JsonArrayRequest {
    private ListResponse<T> mListener;

    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ListRequest(int method, String url, ListResponse<T> listener, Response.ErrorListener errorListener) {
        super(method, url, null, null, errorListener);
        mListener = listener;
    }

    protected ListResponse<T> getListResponse() {
        return mListener;
    }

    protected List<T> json2list(JSONArray jsonArray) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArray.toString(), new TypeToken<List<T>>() {
        }.getType());
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        mListener.onResponse(json2list(response));
    }

    /**
     * Created by presisco on 2017/5/17.
     */

    public interface ListResponse<T> {
        void onResponse(List<T> response);
    }
}
