package com.javimartd.theguardian.presentation.news

//import kotlinx.android.synthetic.main.activity_news.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.domain.model.DailyNews
import com.javimartd.theguardian.domain.usecases.GetNews
import com.javimartd.theguardian.presentation.base.ToolbarManager
import com.javimartd.theguardian.presentation.extensions.toggleVisibility
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class NewsActivity : AppCompatActivity(), NewsView, ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private var recycler : RecyclerView? = null
    private var progress: ProgressBar? = null
    private var presenter : NewsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setUpViews()
        setUpPresenter()
        loadNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun updateLoading() {
        progress?.toggleVisibility()
    }

    //region private methods

    private fun setUpPresenter() {
        presenter = NewsPresenter()
        presenter?.attachView(this)
    }

    private fun loadNews() {
        doAsync {
            val result = GetNews().execute()
            uiThread {
                toast("Request completed")
                updateLoading()
                showNews(result)
            }
        }
    }

    private fun showNews(result: DailyNews) {
        recycler?.toggleVisibility()
        recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = NewsAdapter(result) {toast(it.section.getTitle()) }
    }

    private fun setUpViews() {
        recycler = findViewById(R.id.recycler)
        progress = findViewById(R.id.progressBar)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.app_name)
    }

    //endregion
}
