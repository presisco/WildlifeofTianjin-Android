package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

/**
 * Created by presisco on 2017/5/16.
 */

public abstract class ObjectRequest<T> extends JsonObjectRequest {
    private ObjectResponse<T> mListener;
    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ObjectRequest(int method, String url, ObjectResponse<T> listener, Response.ErrorListener errorListener) {
        super(method, url, null, null, errorListener);
        mListener = listener;
    }

    protected ObjectResponse<T> getTaskResponse() {
        return mListener;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        getTaskResponse().onResponse(json2object(response));
    }

    protected T json2object(JSONObject object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<T>() {
        }.getType());
    }

    /**
     * Created by presisco on 2017/5/16.
     */

    public interface ObjectResponse<T> {
        void onResponse(T response);
    }
}
