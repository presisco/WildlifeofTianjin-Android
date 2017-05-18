package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Constants;

import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public class SearchCreatureRequest extends ListRequest<CreatureOverview> {
    private String name;

    /**
     * Creates a new request.
     *
     * @param name          search name
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public SearchCreatureRequest(String name, ListResponse<CreatureOverview> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Constants.PATH_SEARCH_CREATURE, listener, errorListener);
        this.name = name;
    }

    /**
     * Returns a list of extra HTTP headers to go along with this request. Can
     * throw {@link AuthFailureError} as authentication may be required to
     * provide these values.
     *
     * @throws AuthFailureError In the event of auth failure
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return null;
    }
}
