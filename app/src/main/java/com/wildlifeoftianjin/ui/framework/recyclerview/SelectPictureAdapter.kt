package com.wildlifeoftianjin.ui.framework.recyclerview

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.utils.ImageUtils
import java.util.*

/**
 * Created by presisco on 2017/5/18.
 */

class SelectPictureAdapter(private val mContext: Context, private val onAdd: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mPictures = ArrayList<Uri>()
    private val mThumbnails = ArrayList<Bitmap>()
    private var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    private val onDeletePicture: (position: Int) -> Unit = {
        position ->
        mPictures.removeAt(position)
        mThumbnails.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PictureHolder -> holder.imageView.setImageBitmap(mThumbnails[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        VIEW_TYPE_ADD_BUTTON -> AddButtonHolder(mInflater.inflate(R.layout.listitem_selected_picture, parent, false), onAdd)
        else -> PictureHolder(mInflater.inflate(R.layout.listitem_selected_picture, parent, false), onDeletePicture)
    }

    fun addPicture(uri: Uri) {
        val thumbnail = ImageUtils.getThumbnail(mContext, uri, 512, 512)
        if (thumbnail != null) {
            mPictures.add(uri)
            mThumbnails.add(thumbnail)
            notifyItemInserted(mPictures.size - 1)
        }
    }

    val pictures: List<Uri>
        get() = mPictures

    override fun getItemCount() = mPictures.size + 1

    override fun getItemViewType(position: Int): Int = if (position < mPictures.size) {
        VIEW_TYPE_PICTURE
    } else {
        VIEW_TYPE_ADD_BUTTON
    }

    class PictureHolder(itemView: View, listener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageMain) as ImageView

        init {
            itemView.findViewById(R.id.imageDelete).setOnClickListener { listener(adapterPosition) }
        }
    }

    class AddButtonHolder(itemView: View, listener: () -> Unit) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.findViewById(R.id.imageDelete).visibility = GONE
            val buttonImage = itemView.findViewById(R.id.imageMain) as ImageView
            buttonImage.setOnClickListener { listener() }
            buttonImage.setImageResource(R.drawable.ic_add_picture)
        }
    }

    companion object {
        private val VIEW_TYPE_PICTURE = 10
        private val VIEW_TYPE_ADD_BUTTON = 20
    }
}
