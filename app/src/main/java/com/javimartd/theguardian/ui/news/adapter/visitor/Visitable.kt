package com.javimartd.theguardian.ui.news.adapter.visitor

interface Visitable {
    fun type(typeFactory: TypeFactory): Int
}