package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.network.Constants;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public class RecordRequest extends ObjectRequest<Record> {
    private int record_id;

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public RecordRequest(int record_id, TaskResponse<Record> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Constants.PATH_REQUEST_RECORD, listener, errorListener);
        this.record_id = record_id;
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
    protected void deliverResponse(JSONObject response) {
        getTaskResponse().onResponse(null);
    }
}
