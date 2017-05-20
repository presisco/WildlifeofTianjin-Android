package com.wildlifeoftianjin.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.android.volley.Response
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.network.Constants
import com.wildlifeoftianjin.network.Request.PostFormRequest
import com.wildlifeoftianjin.ui.fragment.EditRecordFragment

/**
 * Created by presisco on 2017/5/18.
 */

class EditRecordActivity : NetworkActivity(), Response.ErrorListener {

    private var mEditRecordFragment: EditRecordFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)

        mEditRecordFragment = EditRecordFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.record_fragment, mEditRecordFragment)
        transaction.commit()

        val creature_name = intent.getStringExtra(KEY_CREATURE_NAME)
        val creature_id = intent.getStringExtra(KEY_CREATURE_ID)
        mEditRecordFragment!!.setCreature(creature_name, creature_id)
    }

    fun onUpload(v: View): Unit {
        val record = mEditRecordFragment!!.record
        requestQueue.add(PostFormRequest(Constants.PATH_UPLOAD_RECORD, record, onResponse, this))
        showLoadingIndicator()
    }

    val onResponse = {
        result: Map<String, String> ->
        var toast_content = ""
        if (result["result"] === "1") {
            toast_content = "upload succeed"
        } else {
            toast_content = "upload failed"
        }
        Toast.makeText(this, toast_content, Toast.LENGTH_SHORT).show()
        hideLoadingIndicator()
    }

    companion object {
        val KEY_CREATURE_NAME = "name"
        val KEY_CREATURE_ID = "id"
    }
}
