package com.javimartd.theguardian.ui.news

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.ui.base.ToolbarManager
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.dialogs.LoadingDialog
import com.javimartd.theguardian.ui.extensions.showSnackbar
import com.javimartd.theguardian.ui.news.model.NewsViewModel
import com.javimartd.theguardian.ui.webView.WebViewActivity
import kotlinx.android.synthetic.main.activity_news.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class NewsActivity : BaseActivity(), NewsView, ToolbarManager {

    @Inject lateinit var presenter: NewsPresenter

    @Inject lateinit var loading: LoadingDialog

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setUpDagger()
        setUpUI()
        setUpPresenter()
    }

    private fun setUpDagger() {
        (application as TheGuardianApplication).component.plus(this)
    }

    override fun showLoading() {
        loading.showDialog()
    }

    override fun hideLoading() {
        loading.cancelDialog()
    }

    override fun showConnectionError() {
        constraintNewsActivity.showSnackbar(getString(R.string.connection_error),
                Snackbar.LENGTH_LONG)
    }

    override fun showGenericError() {
        constraintNewsActivity.showSnackbar(getString(R.string.generic_error),
                Snackbar.LENGTH_LONG)
    }

    /********************
     * private functions
     ********************/

    private fun setUpPresenter() {
        presenter.attachView(this)
    }

    override fun showNews(news: List<NewsViewModel>) {
        adapter.items = news
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
}
