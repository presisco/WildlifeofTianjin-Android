package com.wildlifeoftianjin.ui.framework.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.android.volley.toolbox.NetworkImageView
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.CreatureOverview

/**
 * Created by presisco on 2017/5/16.
 */

class CreatureListAdapter(context: Context, onFooter: () -> Unit, val onClickedCreature: (Int) -> Unit)
    : DragAppendAdapter<CreatureOverview>(context),
        DragAppendAdapter.ActionListener {

    init {
        super.onFooter = onFooter
        onAction = this
    }

    override fun onCreate(inflater: LayoutInflater, parent: ViewGroup): DragAppendAdapter.ContentHolder {
        return CreatureHolder(
                inflater.inflate(
                        R.layout.listitem_creature_overview, parent, false), onClickedCreature)
    }

    override fun onBind(holder: DragAppendAdapter.ContentHolder, position: Int) {
        val creatureHolder = holder as CreatureHolder
        val overview = getItem(position)
        creatureHolder.nameText.text = overview.name
        creatureHolder.imageView.setImageUrl(overview.image, getImageLoader())
    }

    override fun onRecycled(holder: DragAppendAdapter.ContentHolder) {

    }

    class CreatureHolder(itemView: View, listener: (Int) -> Unit) : DragAppendAdapter.ContentHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.textCreatureName) as TextView
        val imageView: NetworkImageView = itemView.findViewById(R.id.imageCreature) as NetworkImageView

        init {
            imageView.setDefaultImageResId(R.drawable.ic_network_loading)
            imageView.setErrorImageResId(R.drawable.ic_warning)
            itemView.setOnClickListener { listener(adapterPosition) }
        }
    }
}
