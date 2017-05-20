package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.wildlifeoftianjin.model.RecordOverview;
import com.wildlifeoftianjin.network.Constants;

/**
 * Created by presisco on 2017/5/16.
 */

public class CreatureRecordListRequest extends ListRequest<RecordOverview> {

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public CreatureRecordListRequest(String creature_id, int page, ListResponse<RecordOverview> listener, Response.ErrorListener errorListener) {
        super(Constants.PATH_REQUEST_CREATURE_RECORD_LIST, RecordOverview.class, listener, errorListener);
        putUrlParams("id", creature_id);
        putUrlParams("page", page);
    }
}
