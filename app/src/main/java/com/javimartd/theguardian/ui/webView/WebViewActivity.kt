package com.javimartd.theguardian.ui.webView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.databinding.ActivityWebViewBinding
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.common.BaseWebViewClient
import com.javimartd.theguardian.ui.common.ToolbarManager
import com.javimartd.theguardian.ui.dialogs.LoadingDialog
import com.javimartd.theguardian.ui.extensions.snack
import javax.inject.Inject

class WebViewActivity: BaseActivity(), WebViewContract.View, ToolbarManager {

    companion object {
        const val URL = "WebViewActivity:url"

        fun newIntent(context: Context, url: String) : Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(URL, url)
            }
        }
    }

    @Inject lateinit var baseWebViewClient: BaseWebViewClient

    private lateinit var binding: ActivityWebViewBinding
    private lateinit var loading: LoadingDialog

    override val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        loadURL()
    }

    override fun onDestroy() {
        super.onDestroy()
        loading.onDetach()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun showError() {
        binding.constraintWebViewActivity.snack(getString(R.string.snackBar_text), Snackbar.LENGTH_INDEFINITE) {
            setAction(getString(R.string.snackBar_action)) { finish() }
        }
    }

    override fun showLoading() = loading.showDialog()

    override fun hideLoading() = loading.hideDialog()

    private fun setUpUI() {
        setUpToolbar()
        loading = LoadingDialog(this)
        setWebViewSettings()
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.app_name)
        enableHomeAsUp{ onBackPressed() }
        initializeToolbar { shareNews() }
        setSettingsButtonToInvisible()
        if (null != getShareIntent().resolveActivity(this.packageManager)) {
            setShareButtonToVisible()
        }
    }

    private fun loadURL() {
        val url = getURL()
        binding.webView.loadUrl(url)
    }

    private fun getURL(): String {
        return intent.getStringExtra(URL)
                ?: throw IllegalArgumentException("Required parameter $URL is missing in the intent.")
    }

    private fun setWebViewSettings() {
        baseWebViewClient.setWebViewClientInterface(this)
        binding.webView.webViewClient = baseWebViewClient
        binding.webView.isLongClickable = true
        binding.webView.settings.apply {
            javaScriptEnabled = false
            allowContentAccess = false
            allowFileAccess = false
        }
    }

    private fun shareNews() {
        val shareIntent = Intent.createChooser(getShareIntent(), null)
        startActivity(shareIntent)
    }

    private fun getShareIntent(): Intent {
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_url_message, getURL()))
            type = "text/plain"
        }
    }
}
