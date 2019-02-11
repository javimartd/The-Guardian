package com.javimartd.theguardian.ui.common

interface RequestView {
    fun showLoading()
    fun hideLoading()
    fun showConnectionError()
    fun showError(errorCode: Int, message: String)
    fun showGenericError()
}