package com.wildlifeoftianjin.network.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.ArticleOverview;

import java.util.List;

/**
 * Created by presisco on 2017/1/22.
 */

public class ArticleListRequest extends BaseRequest<List<ArticleOverview>> {
    /**
     * Creates a new request.
     *
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ArticleListRequest(String url, OnParseCompleteListener<List<ArticleOverview>> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Response<List<ArticleOverview>> parseNetworkResponse(NetworkResponse response) {
        return null;
    }
}
