package com.javimartd.theguardian.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.base.ToolbarManager
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.common.state.Resource
import com.javimartd.theguardian.ui.di.ViewModelFactory
import com.javimartd.theguardian.ui.dialogs.LoadingDialog
import com.javimartd.theguardian.ui.extensions.showSnack
import com.javimartd.theguardian.ui.news.model.NewsView
import com.javimartd.theguardian.ui.news.viewmodel.NewsViewModel
import com.javimartd.theguardian.ui.webView.WebViewActivity
import kotlinx.android.synthetic.main.activity_news.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.net.UnknownHostException
import javax.inject.Inject


class NewsActivity: BaseActivity(), ToolbarManager {

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, NewsActivity::class.java)
        }
    }

    @Inject lateinit var loading: LoadingDialog
    @Inject lateinit var factory: ViewModelFactory

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private lateinit var adapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setUpUI()
        setUpViewModel()
    }

    override fun onResume() {
        super.onResume()
        newsViewModel.fetchNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoading()
    }

    private fun showLoading() {
        swipeRefresh.isRefreshing = false
        loading.showDialog()
    }

    private fun hideLoading() {
        swipeRefresh.isRefreshing = false
        loading.hideDialog()
    }

    private fun showNews(news: List<NewsView>) {
        adapter.items = news
    }

    private fun showEmptyState() {
        swipeRefresh.showSnack(getString(R.string.no_news), Snackbar.LENGTH_LONG)
    }

    private fun showConnectionError() {
        swipeRefresh.showSnack(getString(R.string.connection_error), Snackbar.LENGTH_LONG)
    }

    private fun showError(message: String) {
        swipeRefresh.showSnack(message, Snackbar.LENGTH_LONG)
    }

    private fun setUpUI() {
        setUpToolbar()
        swipeRefresh.setOnRefreshListener {
            newsViewModel.fetchNews()
        }
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

    private fun setUpViewModel() {
        newsViewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)

        /**
         * Observe the LiveData, passing in:
         * - activity as the LifecycleOwner: NewsActivity
         * - the observer
         */
        newsViewModel.newsObservable.observe(this, Observer<Resource<List<NewsView>>> {
            it?.let {
                handleDataState(it)
            }
        })
    }

    private fun handleDataState(resource: Resource<List<NewsView>>) {
        when (resource) {
            is Resource.Success -> {
                resource.data?.let {
                    hideLoading()
                    if (resource.data.isEmpty()) {
                        showEmptyState()
                    } else {
                        showNews(it)
                    }
                }
            }
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Error -> {
                hideLoading()
                if (resource.error is UnknownHostException) {
                    showConnectionError()
                } else {
                    resource.message?.let { showError(it) }
                }
            }
        }
    }
}
