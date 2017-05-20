package com.wildlifeoftianjin.network.Request

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by presisco on 2017/5/19.
 */

class PostFormRequest
(url: String, val form: Map<String, String>, val response: (Map<String, String>) -> Unit, errorListener: Response.ErrorListener)
    : StringRequest(url, null, errorListener) {

    /**
     * Return the method for this request.  Can be one of the values in [Method].
     */
    override fun getMethod(): Int {
        return Method.POST
    }

    override fun deliverResponse(response: String) {
        response(json2map(response))
    }

    fun json2map(raw_string: String): Map<String, String> {
        val response = HashMap<String, String>()
        val json_obj = JSONObject(raw_string)
        val keys_itr = json_obj.keys()
        try {
            while (keys_itr.hasNext()) {
                val key = keys_itr.next()
                response.put(key, json_obj.getString(key))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return response
    }

    /**
     * Returns a Map of parameters to be used for a POST or PUT request.  Can throw
     * [AuthFailureError] as authentication may be required to provide these values.
     *
     *
     *
     * Note that you can directly override [.getBody] for custom data.

     * @throws AuthFailureError in the event of auth failure
     */
    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return form
    }
}
