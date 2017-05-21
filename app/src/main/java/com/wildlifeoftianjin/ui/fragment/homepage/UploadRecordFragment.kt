package com.wildlifeoftianjin.ui.fragment.homepage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.android.volley.Response
import com.presisco.lcat.LCAT
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.network.Constants
import com.wildlifeoftianjin.network.Request.PostFormRequest
import com.wildlifeoftianjin.ui.fragment.EditRecordFragment
import com.wildlifeoftianjin.ui.fragment.NetworkFragment

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the [UploadRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UploadRecordFragment : NetworkFragment(), Response.ErrorListener {

    private var mEditRecordFragment: EditRecordFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEditRecordFragment = EditRecordFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_upload_record, container, false)

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.recordFragment, mEditRecordFragment)
        transaction.commit()

        rootView.findViewById(R.id.buttonUpload).setOnClickListener { v -> onUpload(v) }

        rootView.findViewById(R.id.imageSave).setOnClickListener { v -> onSave(v) }

        return rootView
    }

    fun onUpload(v: View) {
        val record = mEditRecordFragment!!.record

        requestQueue.add(PostFormRequest(Constants.PATH_UPLOAD_RECORD, record, onResponse, this))
        showLoadingIndicator()
    }

    fun onSave(v: View) {
        LCAT.d(this, "onSave")
    }

    val onResponse = {
        result: Map<String, String> ->
        var toast_content = ""
        if (result["status"] == "1") {
            toast_content = "upload succeed"
        } else {
            toast_content = "upload failed"
        }
        Toast.makeText(context, toast_content, Toast.LENGTH_SHORT).show()
        hideLoadingIndicator()
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment SearchFragment.
         */
        fun newInstance(): UploadRecordFragment {
            val fragment = UploadRecordFragment()
            return fragment
        }
    }

}// Required empty public constructor
