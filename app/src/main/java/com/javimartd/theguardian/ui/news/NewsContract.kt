package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.ui.common.BaseContract
import com.javimartd.theguardian.ui.common.RequestView
import com.javimartd.theguardian.ui.news.model.NewsViewModel

interface NewsContract {

    interface View: RequestView, BaseContract.View {
        fun showNews(news: List<NewsViewModel>)
        fun showEmptyState()
    }

    interface Presenter: BaseContract.Presenter<View>
}