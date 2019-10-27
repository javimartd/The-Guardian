package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.ui.common.BasePresenter
import com.javimartd.theguardian.ui.common.BaseView
import com.javimartd.theguardian.ui.news.model.NewsViewModel

interface NewsContract {

    interface View: BaseView<Presenter> {
        fun showNews(news: List<NewsViewModel>)
        fun showEmptyState()
        fun showLoading()
        fun hideLoading()
        fun showConnectionError()
        fun showError()
    }

    interface Presenter: BasePresenter {
        fun getNews()
    }
}