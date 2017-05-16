package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.RecordOverview;
import com.wildlifeoftianjin.network.Constants;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public class RecordListRequest extends ListRequest<List<RecordOverview>> {
    private int creature_id;

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public RecordListRequest(int creature_id, TaskResponse<List<RecordOverview>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Constants.PATH_REQUEST_RECORD_LIST, listener, errorListener);
        this.creature_id = creature_id;
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
