package com.javimartd.theguardian.ui.common

interface WebClient {
    fun showLoading()
    fun hideLoading()
    fun errorLoading()
}