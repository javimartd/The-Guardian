package com.javimartd.theguardian.ui.common

interface RequestView {
    fun showLoading()
    fun hideLoading()
    fun showConnectionError()
    fun showGenericError()
}