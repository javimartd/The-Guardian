package com.javimartd.theguardian.ui.common

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import javax.inject.Inject

class BaseWebViewClient @Inject constructor(): WebViewClient() {

    private lateinit var webClient: WebClient

    fun setWebViewClientInterface(webClient: WebClient) {
        this.webClient = webClient
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        webClient.hideLoading()
        webClient.showError()
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        webClient.showLoading()
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        webClient.hideLoading()
    }
}