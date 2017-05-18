package com.wildlifeoftianjin.ui.framework.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.ArticleOverview

/**
 * Created by presisco on 2017/5/16.
 */

class ArticleListAdapter(context: Context, val onClickedArticle: (Int) -> Unit, onFooter: () -> Unit)
    : DragAppendAdapter<ArticleOverview>(context),
        DragAppendAdapter.ActionListener {

    init {
        super.onFooter = onFooter
        onAction = this
    }

    override fun onCreate(inflater: LayoutInflater, parent: ViewGroup): ContentHolder? {
        return ArticleHolder(inflater.inflate(R.layout.listitem_article_overview, parent, false), onClickedArticle)
    }

    override fun onBind(holder: ContentHolder, position: Int) {
    }

    override fun onRecycled(holder: ContentHolder) {
    }

    class ArticleHolder(itemView: View, listener: (Int) -> Unit) : ContentHolder(itemView)
}
