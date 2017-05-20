package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Constants;

/**
 * Created by presisco on 2017/5/16.
 */

public class SearchCreatureRequest extends ListRequest<CreatureOverview> {
    private String name;
    private int page;

    /**
     * Creates a new request.
     *
     * @param name          search name
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public SearchCreatureRequest(String name, int page, ListResponse<CreatureOverview> listener, Response.ErrorListener errorListener) {
        super(Constants.PATH_SEARCH_CREATURE, CreatureOverview.class, listener, errorListener);
        putUrlParams("key", name);
        putUrlParams("page", page);
    }
}
