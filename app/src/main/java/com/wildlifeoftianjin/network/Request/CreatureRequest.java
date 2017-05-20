package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.wildlifeoftianjin.model.Creature;
import com.wildlifeoftianjin.network.Constants;

/**
 * Created by presisco on 2017/5/16.
 */

public class CreatureRequest extends ObjectRequest<Creature> {
    /**
     * Creates a new request.
     *
     * @param creature_id   the id of requested creature
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public CreatureRequest(String creature_id, ObjectResponse<Creature> listener, Response.ErrorListener errorListener) {
        super(Constants.PATH_REQUEST_CREATURE, Creature.class, listener, errorListener);
        putUrlParams("id", creature_id);
    }
}
