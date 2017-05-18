package com.wildlifeoftianjin.ui.fragment


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ToggleButton
import com.presisco.lcat.LCAT
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.Record
import com.wildlifeoftianjin.ui.framework.recyclerview.SelectPictureAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [EditRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditRecordFragment : Fragment() {

    private var creature_name = ""

    private var mPictureAdapter: SelectPictureAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    infix fun View.findEdit(id: Int): EditText = findViewById(id) as EditText
    infix fun View.findToggle(id: Int): ToggleButton = findViewById(id) as ToggleButton

    fun EditText.getString(): String = text.toString()
    fun EditText.getInt(): Int = Integer.parseInt(text.toString())

    private var specieEdit: EditText? = null
    private var countEdit: EditText? = null
    private var timeEdit: EditText? = null
    private var locationEdit: EditText? = null
    private var descriptionEdit: EditText? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_edit_record, container, false)

        (rootView findToggle R.id.toggleLock).setOnCheckedChangeListener { buttonView, isChecked -> }

        specieEdit = rootView findEdit R.id.editSpecie
        countEdit = rootView findEdit R.id.editCount
        timeEdit = rootView findEdit R.id.editTime
        locationEdit = rootView findEdit R.id.editLocation
        descriptionEdit = rootView findEdit R.id.editDescription

        specieEdit!!.setText("")
        timeEdit!!.setText(
                Record.DATE_TIME_FORMAT.format(System.currentTimeMillis()))

        val listImage = rootView.findViewById(R.id.listImage) as RecyclerView
        listImage.layoutManager = GridLayoutManager(context, 4)
        mPictureAdapter = SelectPictureAdapter(context, onAddPicture)
        listImage.adapter = mPictureAdapter

        return rootView
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to [Activity.onStop] of the containing
     * Activity's lifecycle.
     */
    override fun onStop() {
        super.onStop()
        LCAT.d(this, "onStop")
    }

    private val onAddPicture: () -> Unit = {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, REQUEST_CODE_PICK_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                mPictureAdapter!!.addPicture(data!!.data)
            }
        }
    }

    fun setCreatureName(name: String) {
        if (specieEdit != null) {
            specieEdit!!.setText(name)
        } else {
            creature_name = name
        }
    }

    val record: Record
        get() {
            val record = Record()
            record.classification = specieEdit!!.getString()
            record.location = locationEdit!!.getString()
            record.count = countEdit!!.getInt()
            record.description = descriptionEdit!!.getString()
            record.time = timeEdit!!.getString()
            return record
        }

    val pictures: List<Uri>
        get() = mPictureAdapter!!.pictures

    companion object {
        private val REQUEST_CODE_PICK_PICTURE = 10
    }
}// Required empty public constructor
