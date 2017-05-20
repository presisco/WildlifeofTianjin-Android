package com.wildlifeoftianjin.network.Request;

import com.android.volley.Response;
import com.wildlifeoftianjin.model.RecordOverview;

import static com.wildlifeoftianjin.network.Constants.PATH_REQUEST_USER_RECORD_LIST;

/**
 * Created by presisco on 2017/5/19.
 */

public class UserRecordListRequest extends ListRequest<RecordOverview> {
    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public UserRecordListRequest(String user_id, int page, ListResponse<RecordOverview> listener, Response.ErrorListener errorListener) {
        super(PATH_REQUEST_USER_RECORD_LIST, RecordOverview.class, listener, errorListener);
        putUrlParams("id", user_id);
        putUrlParams("page", page);
    }
}
