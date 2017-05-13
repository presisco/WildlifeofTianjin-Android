package com.wildlifeoftianjin.network.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.wildlifeoftianjin.network.Constants;

/**
 * Created by presisco on 2017/1/22.
 */

public abstract class BaseRequest<T> extends Request<T> {
    public static final int METHOD_DEFAULT = 0;
    private OnParseCompleteListener<T> mParseCompleteListener = null;

    /**
     * Creates a new request with the given method (one of the values from {@link Method}),
     * URL, and error listener.  Note that the normal response listener is not provided here as
     * delivery of responses is provided by subclasses, who have a better idea of how to deliver
     * an already-parsed response.
     *
     * @param path
     * @param parseCompleteListener
     * @param errorListener
     */
    public BaseRequest(String path, OnParseCompleteListener<T> parseCompleteListener, Response.ErrorListener errorListener) {
        super(METHOD_DEFAULT, Constants.HOST + path, errorListener);
        mParseCompleteListener = parseCompleteListener;
    }

    /**
     * Delivers error message to the ErrorListener that the Request was
     * initialized with.
     *
     * @param error Error details
     */
    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    /**
     * Subclasses can override this method to parse 'networkError' and return a more specific error.
     * <p>
     * <p>The default implementation just returns the passed 'networkError'.</p>
     *
     * @param volleyError the error retrieved from the network
     * @return an NetworkError augmented with additional information
     */
    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    /**
     * Subclasses must implement this to parse the raw network response
     * and return an appropriate response type. This method will be
     * called from a worker thread.  The response will not be delivered
     * if you return null.
     *
     * @param response Response from the network
     * @return The parsed response, or null in the case of an error
     */
    @Override
    protected abstract Response<T> parseNetworkResponse(NetworkResponse response);

    /**
     * Subclasses must implement this to perform delivery of the parsed
     * response to their listeners.  The given response is guaranteed to
     * be non-null; responses that fail to parse are not delivered.
     *
     * @param response The parsed response returned by
     *                 {@link #parseNetworkResponse(NetworkResponse)}
     */
    @Override
    protected void deliverResponse(T response) {
        mParseCompleteListener.onParseComplete(response);
    }

    public interface OnParseCompleteListener<T> {
        void onParseComplete(T result);
    }
}
