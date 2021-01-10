package com.javimartd.theguardian.ui.news.model

import com.javimartd.theguardian.ui.news.adapter.visitor.TypeFactory
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable

class AdView: Visitable {
    override fun type(typeFactory: TypeFactory): Int {
        return typeFactory.type(this)
    }
}