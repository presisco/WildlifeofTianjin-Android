package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Constants;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by presisco on 2017/1/22.
 */

public class SearchFrontPageRequest extends ListRequest<List<CreatureOverview>> {

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public SearchFrontPageRequest(TaskResponse<List<CreatureOverview>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Constants.PATH_SEARCH_FRONT_PAGE, listener, errorListener);
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

    @Override
    protected void deliverResponse(JSONArray response) {
        getTaskResponse().onResponse(null);
    }
}
