package com.wildlifeoftianjin.ui.framework.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.RecordOverview

/**
 * Created by presisco on 2017/5/17.
 */

class RecordListAdapter(context: Context, onFooter: () -> Unit, private val onClickedRecord: (position: Int) -> Unit)
    : DragAppendAdapter.ActionListener, DragAppendAdapter<RecordOverview>(context) {

    init {
        onAction = this
        super.onFooter = onFooter
    }

    override fun onCreate(inflater: LayoutInflater, parent: ViewGroup): DragAppendAdapter.ContentHolder {
        return RecordHolder(inflater.inflate(R.layout.listitem_record_overview, parent, false), onClickedRecord)
    }

    override fun onBind(holder: DragAppendAdapter.ContentHolder, position: Int) {
        val recordHolder = holder as RecordHolder
        val overview = getItem(position)
        recordHolder.timeText.text = overview.getTime()
        recordHolder.locationText.text = overview.getLocation()
        recordHolder.countText.text = overview.getCount()
        recordHolder.headerText.text = overview.getUsername()
    }

    override fun onRecycled(holder: DragAppendAdapter.ContentHolder) {

    }

    class RecordHolder(itemView: View, listener: (position: Int) -> Unit) : DragAppendAdapter.ContentHolder(itemView) {
        val timeText: TextView = itemView.findViewById(R.id.textTime) as TextView
        val locationText: TextView = itemView.findViewById(R.id.textLocation) as TextView
        val countText: TextView = itemView.findViewById(R.id.textCount) as TextView
        val headerText: TextView = itemView.findViewById(R.id.textHeader) as TextView

        init {
            itemView.setOnClickListener { listener(adapterPosition) }
        }
    }
}
