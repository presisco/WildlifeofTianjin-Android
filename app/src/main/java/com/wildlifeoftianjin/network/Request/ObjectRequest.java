package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wildlifeoftianjin.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public abstract class ObjectRequest<T> extends StringRequest {
    private String baseUrl;
    private ObjectResponse<T> mListener;
    private Map<String, String> mUrlParams = new HashMap<>();
    private Class<T> mClass;

    /**
     * Creates a new request.
     *
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ObjectRequest(String url, Class<T> mClass, ObjectResponse<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, errorListener);
        baseUrl = url;
        mListener = listener;
        this.mClass = mClass;
    }

    /**
     * Returns the URL of this request.
     */
    @Override
    public String getUrl() {
        StringBuilder sb = new StringBuilder(baseUrl);
        if (mUrlParams.size() > 0) {
            sb.append("?");
            for (String key : mUrlParams.keySet()) {
                sb.append(key);
                sb.append("=");
                sb.append(mUrlParams.get(key));
                sb.append("&");
            }
        }
        String result = sb.substring(0, sb.length() - 1);
        return result;
    }

    protected void putUrlParams(String key, String value) {
        mUrlParams.put(key, value);
    }

    protected void putUrlParams(String key, Integer value) {
        mUrlParams.put(key, value.toString());
    }

    protected ObjectResponse<T> getTaskResponse() {
        return mListener;
    }

    @Override
    protected void deliverResponse(String response) {
        getTaskResponse().onResponse(json2object(response));
    }

    protected T json2object(String raw_string) {
        Gson gson = new Gson();
        return gson.fromJson(JsonUtils.getJsonObjectFromMix(raw_string), mClass);
    }

    /**
     * Created by presisco on 2017/5/16.
     */

    public interface ObjectResponse<T> {
        void onResponse(T response);
    }
}
