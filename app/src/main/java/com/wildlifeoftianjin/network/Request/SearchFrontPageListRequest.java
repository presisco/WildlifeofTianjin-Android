package com.wildlifeoftianjin.network.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Constants;

import java.util.List;

/**
 * Created by presisco on 2017/1/22.
 */

public class SearchFrontPageListRequest extends BaseRequest<List<CreatureOverview>> {
    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public SearchFrontPageListRequest(OnParseCompleteListener<List<CreatureOverview>> listener, Response.ErrorListener errorListener) {
        super(Constants.PATH_SEARCH_FRONT_PAGE, listener, errorListener);
    }

    @Override
    protected Response<List<CreatureOverview>> parseNetworkResponse(NetworkResponse response) {
        return null;
    }
}
