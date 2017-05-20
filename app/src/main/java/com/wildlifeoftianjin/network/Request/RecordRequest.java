package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.network.Constants;

/**
 * Created by presisco on 2017/5/16.
 */

public class RecordRequest extends ObjectRequest<Record> {
    private String record_id;

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public RecordRequest(String record_id, ObjectResponse<Record> listener, Response.ErrorListener errorListener) {
        super(Constants.PATH_REQUEST_RECORD, Record.class, listener, errorListener);
        putUrlParams("id", record_id);
    }
}
