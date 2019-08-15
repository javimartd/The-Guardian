package com.javimartd.theguardian.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.base.ToolbarManager
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.dialogs.LoadingDialog
import com.javimartd.theguardian.ui.extensions.showSnack
import com.javimartd.theguardian.ui.extensions.showSupportTheGuardianAlertDialog
import com.javimartd.theguardian.ui.news.model.NewsViewModel
import com.javimartd.theguardian.ui.webView.WebViewActivity
import kotlinx.android.synthetic.main.activity_news.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class NewsActivity: BaseActivity<NewsContract.View, NewsContract.Presenter>(), NewsContract.View, ToolbarManager {

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, NewsActivity::class.java)
        }
    }

    @Inject lateinit var presenter: NewsContract.Presenter

    @Inject lateinit var loading: LoadingDialog

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private lateinit var adapter: NewsAdapter

    private var supportTheGuardianAlertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setUpUI()
        setUpPresenter()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showLoading() {
        loading.showDialog()
    }

    override fun hideLoading() {
        loading.cancelDialog()
    }

    override fun showNews(news: List<NewsViewModel>) {
        adapter.items = news
    }

    override fun showEmptyState() {
        constraintNewsActivity.showSnack("At this moment we have no news to show you",
                Snackbar.LENGTH_LONG)
    }

    override fun showConnectionError() {
        constraintNewsActivity.showSnack(getString(R.string.connection_error),
                Snackbar.LENGTH_LONG)
    }

    override fun showError(errorCode: Int, message: String) {
        constraintNewsActivity.showSnack("$errorCode $message",
                Snackbar.LENGTH_LONG)
    }

    override fun showGenericError() {
        constraintNewsActivity.showSnack(getString(R.string.generic_error),
                Snackbar.LENGTH_LONG)
    }

    private fun setUpPresenter() {
        presenter.attachView(this)
    }

    private fun setUpUI() {
        setUpToolbar()
        loading.createDialog(this)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter {
            startActivity<WebViewActivity>(WebViewActivity.URL to it.webUrl)
        }
        recycler.adapter = adapter
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.app_name)
        initializeToolbar()
    }

    private fun showSupportTheGuardianAlertDialog() {
        if (supportTheGuardianAlertDialog == null)
            supportTheGuardianAlertDialog = showSupportTheGuardianAlertDialog {
                cancelable = true
                isBackGroundTransparent = false
                subscribeButtonClickListener {

                }
                contributeButtonClickListener {

                }
                onCancelListener {
                    // nothing to do
                }
            }
        supportTheGuardianAlertDialog?.show()
    }
}
