package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wildlifeoftianjin.data.NetResult;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by presisco on 2017/5/18.
 */

public class UploadRequest<T> extends JsonObjectRequest {
    private UploadResponse mListener;

    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public UploadRequest(int method, String url, UploadResponse listener, Response.ErrorListener errorListener) {
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

    protected NetResult json2object(JSONObject object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<NetResult>() {
        }.getType());
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(json2object(response));
    }

    public interface UploadResponse {
        void onResponse(NetResult result);
    }
}
