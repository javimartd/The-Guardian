package com.javimartd.theguardian.ui.common

interface BaseView<in T : BasePresenter> {
    fun setPresenter(presenter: T)
}