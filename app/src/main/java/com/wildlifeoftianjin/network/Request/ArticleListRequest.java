package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.wildlifeoftianjin.model.ArticleOverview;
import com.wildlifeoftianjin.network.Constants;

/**
 * Created by presisco on 2017/1/22.
 */

public class ArticleListRequest extends ListRequest<ArticleOverview> {
    private int page;

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public ArticleListRequest(int page, ListResponse<ArticleOverview> listener, Response.ErrorListener errorListener) {
        super(Constants.PATH_REQUEST_ARTICLE_LIST, ArticleOverview.class, listener, errorListener);
        putUrlParams("page", page);
    }

}
