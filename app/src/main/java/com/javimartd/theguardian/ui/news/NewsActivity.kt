package com.javimartd.theguardian.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.databinding.ActivityNewsBinding
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.common.ToolbarManager
import com.javimartd.theguardian.ui.common.state.Resource
import com.javimartd.theguardian.ui.dialogs.DialogActions
import com.javimartd.theguardian.ui.dialogs.LoadingDialog
import com.javimartd.theguardian.ui.extensions.showSnack
import com.javimartd.theguardian.ui.news.adapter.Adapter
import com.javimartd.theguardian.ui.news.adapter.visitor.TypeFactoryImpl
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable
import com.javimartd.theguardian.ui.news.viewmodel.NewsViewModel
import com.javimartd.theguardian.ui.news.viewmodel.ViewModelFactory
import java.net.UnknownHostException
import javax.inject.Inject


class NewsActivity: BaseActivity(), ToolbarManager {

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, NewsActivity::class.java)
        }
    }

    @Inject lateinit var factory: ViewModelFactory

    override val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar)
    }

    private lateinit var binding: ActivityNewsBinding
    private lateinit var adapter: Adapter
    private lateinit var loading: DialogActions
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        loading.onDetach()
    }

    private fun showLoading() {
        binding.swipeRefresh.isRefreshing = false
        loading.showDialog()
    }

    private fun hideLoading() {
        binding.swipeRefresh.isRefreshing = false
        loading.hideDialog()
    }

    private fun showNews(news: List<Visitable>) {
        adapter.items = news
    }

    private fun showConnectionError() {
        binding.swipeRefresh.showSnack(getString(R.string.connection_error), Snackbar.LENGTH_LONG)
    }

    private fun showError(message: String) {
        binding.swipeRefresh.showSnack(message, Snackbar.LENGTH_LONG)
    }

    private fun setUpUI() {
        setUpToolbar()
        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.fetchNews()
        }
        loading = LoadingDialog(this)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        adapter = Adapter(TypeFactoryImpl())
        binding.recycler.adapter = adapter
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.app_name)
        initializeToolbar()
    }

    private fun setUpViewModel() {
        newsViewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)

        newsViewModel.newsObservable.observe(this) {
            it?.let {
                handleDataState(it)
            }
        }
    }

    private fun handleDataState(resource: Resource<List<Visitable>>) {
        when (resource) {
            is Resource.Success -> {
                resource.data?.let {
                    hideLoading()
                    showNews(it)
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
