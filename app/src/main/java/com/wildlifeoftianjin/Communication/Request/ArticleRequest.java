package com.wildlifeoftianjin.Communication.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.request.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by presisco on 2017/1/22.
 */

public class ArticleRequest extends JsonArrayRequest {
    /**
     * Creates a new request.
     *
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ArticleRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }
}
