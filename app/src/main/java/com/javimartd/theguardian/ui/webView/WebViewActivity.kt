package com.javimartd.theguardian.ui.webView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.google.android.material.snackbar.Snackbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.common.BaseWebViewClient
import com.javimartd.theguardian.ui.common.WebClient
import com.javimartd.theguardian.ui.dialogs.LoadingDialog
import com.javimartd.theguardian.ui.extensions.snack
import kotlinx.android.synthetic.main.activity_web_view.*
import org.jetbrains.anko.bundleOf
import javax.inject.Inject

class WebViewActivity : BaseActivity(), WebClient {

    companion object {
        const val URL = "WebViewActivity:url"

        fun newIntent(context: Context, url: String) : Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtras(bundleOf(URL to url))
            }
        }
    }

    @Inject lateinit var baseWebViewClient: BaseWebViewClient

    @Inject lateinit var loading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setUpUI()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun errorLoading() {
        constraintWebViewActivity.snack(getString(R.string.snackBar_text), Snackbar.LENGTH_INDEFINITE) {
            setAction(getString(R.string.snackBar_action)) { finish() }
        }
    }

    override fun showLoading() = loading.showDialog()

    override fun hideLoading() = loading.cancelDialog()

    /********************
     * private functions
     ********************/

    private fun setUpUI() {
        loading.createDialog(this)
        setWebViewSettings()
        loadURL()
    }

    private fun loadURL() {
        webView.loadUrl(intent.getStringExtra(URL))
    }

    private fun setWebViewSettings() {
        baseWebViewClient.setWebViewClientInterface(this)
        webView.webViewClient = baseWebViewClient
        webView.isLongClickable = true
        webView.settings.apply {
            javaScriptEnabled = false
            allowContentAccess = false
            allowFileAccess = false
            allowFileAccessFromFileURLs = false
            allowUniversalAccessFromFileURLs = false
        }
    }
}
