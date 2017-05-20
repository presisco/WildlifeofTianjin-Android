package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wildlifeoftianjin.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public abstract class ListRequest<T> extends StringRequest {
    private ListResponse<T> mListener;
    private Map<String, String> mUrlParams = new HashMap<>();
    private String baseUrl;
    private Class<T> mClass;

    /**
     * Creates a new request.
     *
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ListRequest(String url, Class<T> mClass, ListResponse<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, errorListener);
        mListener = listener;
        baseUrl = url;
        this.mClass = mClass;
    }

    protected void putUrlParams(String key, String value) {
        mUrlParams.put(key, value);
    }

    protected void putUrlParams(String key, Integer value) {
        mUrlParams.put(key, value.toString());
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

    protected List<T> json2list(String raw_string) {
        ArrayList<T> response = new ArrayList<>();
        JsonArray array = new JsonParser().parse(JsonUtils.getJsonArrayFromMix(raw_string)).getAsJsonArray();
        Gson gson = new Gson();

        for (final JsonElement elem : array) {
            response.add(gson.fromJson(elem, mClass));
        }

        return response;
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(json2list(response));
    }

    /**
     * Created by presisco on 2017/5/17.
     */

    public interface ListResponse<T> {
        void onResponse(List<T> response);
    }
}
