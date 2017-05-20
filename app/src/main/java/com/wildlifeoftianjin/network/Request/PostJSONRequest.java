package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by presisco on 2017/5/18.
 */

public class PostJSONRequest<T> extends JsonObjectRequest {
    private PostResponse mListener;

    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public PostJSONRequest(int method, String url, PostResponse listener, Response.ErrorListener errorListener) {
        super(method, url, null, null, errorListener);
        mListener = listener;
    }

    protected byte[] object2byte(T object) {
        Gson gson = new Gson();
        String converted_json = gson.toJson(object);
        byte[] raw = new byte[0];
        try {
            raw = converted_json.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return raw;
    }

    protected Map<String, String> json2map(JSONObject object) {
        Map<String, String> response = new HashMap<>();
        Iterator<String> keys_itr = object.keys();
        try {
            while (keys_itr.hasNext()) {
                String key = keys_itr.next();
                response.put(key, object.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(json2map(response));
    }

    public interface PostResponse {
        void onResponse(Map<String, String> result);
    }
}
