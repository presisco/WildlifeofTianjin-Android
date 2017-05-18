package com.wildlifeoftianjin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.RecordOverview
import com.wildlifeoftianjin.network.NetQueueSingleton
import com.wildlifeoftianjin.network.Request.ListRequest
import com.wildlifeoftianjin.network.Request.RecordListRequest
import com.wildlifeoftianjin.ui.framework.recyclerview.RecordListAdapter
import kotlinx.android.synthetic.main.activity_record_list.*

class RecordListActivity : AppCompatActivity(), ListRequest.ListResponse<RecordOverview>, Response.ErrorListener {

    private var creature_id: String? = null

    private var mRequestQueue: RequestQueue? = null

    private var mRecordAdapter: RecordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        creature_id = intent.getStringExtra(KEY_CREATURE_ID)
        mRequestQueue = NetQueueSingleton.getInstance(this).requestQueue

        mRecordAdapter = RecordListAdapter(this, onShowingFooter, onClickRecord)
        listRecord.adapter = mRecordAdapter

        mRequestQueue!!.add(RecordListRequest(creature_id, this, this))
    }

    private val onClickRecord = {
        position: Int ->
        startActivity(
                Intent(this, ViewRecordActivity::class.java)
                        .putExtra(
                                ViewRecordActivity.KEY_RECORD_ID,
                                mRecordAdapter!!.getItem(position)
                                        .getRecordID()))
    }

    private val onShowingFooter: () -> Unit = {
        mRequestQueue!!.add(RecordListRequest(creature_id, this, this))
    }

    override fun onErrorResponse(error: VolleyError) {

    }

    override fun onResponse(response: List<RecordOverview>) {
        mRecordAdapter!!.appendDataSet(response)
        mRecordAdapter!!.notifyDataSetChanged()
    }

    companion object {
        val KEY_CREATURE_ID = "creature_id"
    }
}
