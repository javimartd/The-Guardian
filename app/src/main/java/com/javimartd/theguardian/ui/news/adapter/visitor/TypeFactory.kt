package com.javimartd.theguardian.ui.news.adapter.visitor

import android.view.View
import com.javimartd.theguardian.ui.news.adapter.viewholder.BaseViewHolder
import com.javimartd.theguardian.ui.news.model.AdView
import com.javimartd.theguardian.ui.news.model.HeaderView
import com.javimartd.theguardian.ui.news.model.NewsView

interface TypeFactory {
    fun type(header: HeaderView): Int
    fun type(news: NewsView): Int
    fun type(ad: AdView): Int
    fun holder(type: Int, view: View): BaseViewHolder<Visitable>
}