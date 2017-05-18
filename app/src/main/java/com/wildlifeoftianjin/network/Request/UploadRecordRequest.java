package com.wildlifeoftianjin.network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.wildlifeoftianjin.model.Record;
import com.wildlifeoftianjin.network.Constants;

import java.util.Map;

/**
 * Created by presisco on 2017/5/18.
 */

public class UploadRecordRequest extends UploadRequest {
    private Record record;

    /**
     * Creates a new request.
     *
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public UploadRecordRequest(Record record, UploadResponse listener, Response.ErrorListener errorListener) {
        super(Method.POST, Constants.PATH_UPLOAD_RECORD, listener, errorListener);
        this.record = record;
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
    public String getBodyContentType() {
        return Constants.CONTENT_TYPE_JSON;
    }

    @Override
    public byte[] getBody() {
        return object2byte(record);
    }
}
