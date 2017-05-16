package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.Creature;
import com.wildlifeoftianjin.network.Constants;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by presisco on 2017/5/16.
 */

public class CreatureRequest extends ObjectRequest<Creature> {
    private int request_id;

    /**
     * Creates a new request.
     *
     * @param creature_id   the id of requested creature
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public CreatureRequest(int creature_id, TaskResponse<Creature> listener, Response.ErrorListener errorListener) {
        super(Method.GET, Constants.PATH_REQUEST_CREATURE, listener, errorListener);
        request_id = creature_id;
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
