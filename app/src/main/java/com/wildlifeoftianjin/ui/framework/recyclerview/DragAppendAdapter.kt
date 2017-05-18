package com.wildlifeoftianjin.ui.framework.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.android.volley.toolbox.ImageLoader
import com.presisco.lcat.LCAT
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.network.NetQueueSingleton
import java.util.*

/**
 * Created by presisco on 2017/5/16.
 */

open class DragAppendAdapter<CONTENT>(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var show_footer = true
    private var mDataSet: MutableList<CONTENT> = ArrayList()
    protected var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val mNetQueue: NetQueueSingleton = NetQueueSingleton.getInstance(context)
    protected var onAction: ActionListener? = null
        set(value) {}
    protected var onFooter = {}
        set(value) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? = when (viewType) {
        VIEW_TYPE_CONTENT -> onAction!!.onCreate(layoutInflater, parent)
        else -> FooterHolder(layoutInflater.inflate(R.layout.listitem_footer, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentHolder) {
            onAction!!.onBind(holder, position)
        } else {
            onFooter()
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {
        if (holder is ContentHolder) {
            LCAT.d(this, "recycled", "content")
            onAction!!.onRecycled(holder)
        } else {
            LCAT.d(this, "recycled", "footer")
        }
    }

    fun setDataSet(dataset: MutableList<CONTENT>) {
        mDataSet = dataset
    }

    fun appendDataSet(dataset: List<CONTENT>) {
        mDataSet.addAll(dataset)
    }

    override fun getItemViewType(position: Int): Int {
        if (position < mDataSet.size)
            return VIEW_TYPE_CONTENT
        else
            return VIEW_TYPE_FOOTER
    }

    override fun getItemCount(): Int = if (show_footer) {
        mDataSet.size + 1
    } else {
        mDataSet.size
    }

    fun setShowingFooter(flag: Boolean) {
        show_footer = flag
    }

    fun getItem(position: Int) = mDataSet[position]

    fun getImageLoader(): ImageLoader = mNetQueue.imageLoader

    interface ActionListener {
        fun onCreate(inflater: LayoutInflater, parent: ViewGroup): ContentHolder?

        fun onBind(holder: ContentHolder, position: Int)

        fun onRecycled(holder: ContentHolder)
    }

    abstract class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class FooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mProgress: ProgressBar = itemView.findViewById(R.id.listLoadingProgress) as ProgressBar
    }

    companion object {
        protected val VIEW_TYPE_FOOTER = 0
        protected val VIEW_TYPE_CONTENT = 1
    }
}
