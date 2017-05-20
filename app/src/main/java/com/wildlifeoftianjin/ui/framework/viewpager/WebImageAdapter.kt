package com.wildlifeoftianjin.ui.framework.viewpager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.toolbox.NetworkImageView
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.network.NetQueueSingleton

/**
 * Created by presisco on 2017/5/18.
 */

class WebImageAdapter(context: Context) : PagerAdapter() {
    private val mImageLoader = NetQueueSingleton.getInstance(context).imageLoader
    private val mInflater = LayoutInflater.from(context)

    private var mPages = arrayOfNulls<ImagePage>(0)

    private inner class ImagePage(val root: View, val url: String) {
        val image: NetworkImageView = root.findViewById(R.id.image) as NetworkImageView
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * [.finishUpdate].

     * @param container The containing View in which the page will be shown.
     * *
     * @param position  The page position to be instantiated.
     * *
     * @return Returns an Object representing the new page.  This does not
     * * need to be a View, but can be some other container of the page.
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mPages[position]!!.root)
        mPages[position]!!.image.setImageUrl(mPages[position]!!.url, mImageLoader)
        return mPages[position]!!.root
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by [.instantiateItem]. This method is
     * required for a PagerAdapter to function properly.

     * @param view   Page View to check for association with `object`
     * *
     * @param object Object to check for association with `view`
     * *
     * @return true if `view` is associated with the key object `object`
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return mPages.size
    }

    fun setImageUrls(imageUrls: Array<String>) {
        mPages = arrayOfNulls<ImagePage>(imageUrls.size)
        for (i in mPages.indices) {
            mPages[i] = ImagePage(mInflater.inflate(R.layout.page_creature_picture, null), imageUrls[i])
            with(mPages[i]!!.image) {
                setDefaultImageResId(R.drawable.ic_network_loading)
                setErrorImageResId(R.drawable.ic_warning)
            }
        }
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from [.finishUpdate].

     * @param container The containing View from which the page will be removed.
     * *
     * @param position  The page position to be removed.
     * *
     * @param object    The same object that was returned by
     * *                  [.instantiateItem].
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
