package com.javimartd.theguardian.ui.news.adapter.visitor

import android.view.View
import com.javimartd.theguardian.ui.news.adapter.viewholder.AdViewHolder
import com.javimartd.theguardian.ui.news.adapter.viewholder.BaseViewHolder
import com.javimartd.theguardian.ui.news.adapter.viewholder.HeaderViewHolder
import com.javimartd.theguardian.ui.news.adapter.viewholder.NewsViewHolder
import com.javimartd.theguardian.ui.news.model.AdView
import com.javimartd.theguardian.ui.news.model.HeaderView
import com.javimartd.theguardian.ui.news.model.NewsView

class TypeFactoryImpl: TypeFactory {

    override fun type(header: HeaderView): Int {
        return HeaderViewHolder.LAYOUT
    }

    override fun type(news: NewsView): Int {
        return NewsViewHolder.LAYOUT
    }

    override fun type(ad: AdView): Int {
        return AdViewHolder.LAYOUT
    }

    override fun holder(type: Int, view: View): BaseViewHolder<Visitable> {
        return when(type) {
            HeaderViewHolder.LAYOUT -> HeaderViewHolder(view)
            NewsViewHolder.LAYOUT -> NewsViewHolder(view)
            AdViewHolder.LAYOUT -> AdViewHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}