package com.javimartd.theguardian.ui.news.model

import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.news.adapter.visitor.TypeFactory
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable

class HeaderView: Visitable {

    private var title: Int = R.string.header

    override fun type(typeFactory: TypeFactory): Int {
        return typeFactory.type(this)
    }

    fun getTitle() = title
}