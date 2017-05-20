package com.wildlifeoftianjin.ui.fragment


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import com.presisco.lcat.LCAT
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.Record
import com.wildlifeoftianjin.ui.activity.SelectCreatureActivity
import com.wildlifeoftianjin.ui.framework.recyclerview.SelectPictureAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [EditRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditRecordFragment : Fragment() {

    private var creature_name = ""
    private var creature_id = ""

    private var mPictureAdapter: SelectPictureAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    infix fun View.findText(id: Int): TextView = findViewById(id) as TextView
    infix fun View.findEdit(id: Int): EditText = findViewById(id) as EditText
    infix fun View.findToggle(id: Int): ToggleButton = findViewById(id) as ToggleButton

    fun EditText.getString(): String = text.toString()
    fun EditText.getInt(): Int = Integer.parseInt(text.toString())

    private var specieText: TextView? = null
    private var countEdit: EditText? = null
    private var timeEdit: EditText? = null
    private var locationEdit: EditText? = null
    private var descriptionEdit: EditText? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_edit_record, container, false)

        (rootView findToggle R.id.toggleLock).setOnCheckedChangeListener { buttonView, isChecked -> }

        specieText = rootView findText R.id.textSpecie
        countEdit = rootView findEdit R.id.editCount
        timeEdit = rootView findEdit R.id.editTime
        locationEdit = rootView findEdit R.id.editLocation
        descriptionEdit = rootView findEdit R.id.editDescription

        if (creature_name != "") {
            specieText?.text = creature_name
        } else {

            specieText?.setOnClickListener {
                startActivityForResult(Intent(context, SelectCreatureActivity::class.java), REQUEST_CODE_SELECT_CREATURE)
            }
        }

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
        when (requestCode) {
            REQUEST_CODE_PICK_PICTURE -> if (resultCode == Activity.RESULT_OK) mPictureAdapter!!.addPicture(data!!.data)
            REQUEST_CODE_SELECT_CREATURE -> if (resultCode == Activity.RESULT_OK) {
                creature_id = data!!.getStringExtra(SelectCreatureActivity.KEY_CREATURE_ID)
                creature_name = data.getStringExtra(SelectCreatureActivity.KEY_CREATURE_NAME)
            }
        }
    }

    fun setCreature(name: String, id: String) {
        specieText?.text = name
        creature_id = id
        creature_name = name
    }

    val record: Map<String, String>
        get() {
            val record = hashMapOf<String, String>()
            record.put("userID", PreferenceManager.getDefaultSharedPreferences(context).getString("user_id", ""))
            record.put("animalID", creature_id)
            record.put("time", timeEdit!!.getString())
            record.put("location", locationEdit!!.getString())
            record.put("count", countEdit!!.getString())
            record.put("description", descriptionEdit!!.getString())
            return record
        }

    val pictures: List<Uri>
        get() = mPictureAdapter!!.pictures

    companion object {
        private val REQUEST_CODE_PICK_PICTURE = 10
        private val REQUEST_CODE_SELECT_CREATURE = 20
        val KEY_CREATURE_NAME = "creature_name"
        val KEY_CREATURE_ID = "creature_id"
    }
}// Required empty public constructor
