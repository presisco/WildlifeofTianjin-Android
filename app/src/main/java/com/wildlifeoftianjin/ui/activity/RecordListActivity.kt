package com.wildlifeoftianjin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.android.volley.Response
import com.android.volley.VolleyError
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.RecordOverview
import com.wildlifeoftianjin.network.Request.CreatureRecordListRequest
import com.wildlifeoftianjin.network.Request.ListRequest
import com.wildlifeoftianjin.network.Request.UserRecordListRequest
import com.wildlifeoftianjin.ui.framework.recyclerview.RecordListAdapter
import kotlinx.android.synthetic.main.activity_record_list.*

class RecordListActivity : NetworkActivity(), ListRequest.ListResponse<RecordOverview>, Response.ErrorListener {
    private var current_page: Int = 0

    private var mode = MODE_CREATURE

    private var creature_id: String? = null
    private var creature_name: String? = null

    private var user_id: String? = null

    private var mRecordAdapter: RecordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        mRecordAdapter = RecordListAdapter(this, onShowingFooter, onClickRecord)
        listRecord.layoutManager = LinearLayoutManager(this)
        listRecord.adapter = mRecordAdapter
        mRecordAdapter?.setShowingFooter(false)

        mode = intent.getStringExtra(KEY_REQUEST_MODE)

        if (mode == MODE_CREATURE) {
            creature_id = intent.getStringExtra(KEY_CREATURE_ID)
            creature_name = intent.getStringExtra(KEY_CREATURE_NAME)
            requestQueue.add(CreatureRecordListRequest(creature_id, current_page, this, this))
            showLoadingIndicator()

        } else {
            user_id = intent.getStringExtra(KEY_USER_ID)
            requestQueue.add(UserRecordListRequest(user_id, current_page, this, this))
            showLoadingIndicator()
        }
    }

    fun onBack(v: View) {
        finish()
    }

    fun onEdit(v: View) {
        startActivity(Intent(this, EditRecordActivity::class.java)
                .putExtra(EditRecordActivity.KEY_CREATURE_NAME, creature_name)
                .putExtra(EditRecordActivity.KEY_CREATURE_ID, creature_id))
    }

    fun onShare(v: View) {

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
        current_page += 1
        requestQueue.add(CreatureRecordListRequest(creature_id, current_page, this, this))
    }

    override fun onErrorResponse(error: VolleyError) {

    }

    override fun onResponse(response: MutableList<RecordOverview>) {
        if (response.size < 10)
            mRecordAdapter?.setShowingFooter(false)
        else
            mRecordAdapter?.setShowingFooter(true)
        mRecordAdapter!!.appendDataSet(response)
        mRecordAdapter!!.notifyDataSetChanged()
        hideLoadingIndicator()
    }

    companion object {
        val KEY_REQUEST_MODE = "request_mode"
        val MODE_USER = "user"
        val MODE_CREATURE = "creature"
        val KEY_CREATURE_ID = "creature_id"
        val KEY_CREATURE_NAME = "creature_name"
        val KEY_USER_ID = "user_id"
    }
}
