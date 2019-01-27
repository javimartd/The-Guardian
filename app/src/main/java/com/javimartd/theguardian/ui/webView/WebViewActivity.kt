package com.javimartd.theguardian.ui.webView

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.javimartd.theguardian.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val URL = "WebViewActivity:url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setWebViewSettings()
        loadURL()
    }

    /********************
     * private functions
     ********************/

    private fun loadURL() {
        webView.loadUrl(intent.getStringExtra(URL))
    }

    private fun setWebViewSettings() {
        webView.isLongClickable = true
        webView.settings.javaScriptEnabled = false
        webView.settings.allowContentAccess = false
        webView.settings.allowFileAccess = false
        webView.settings.allowFileAccessFromFileURLs = false
        webView.settings.allowUniversalAccessFromFileURLs = false
    }
}
