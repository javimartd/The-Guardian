package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.ui.common.RequestView
import com.javimartd.theguardian.ui.news.model.NewsViewModel

interface NewsView: RequestView {
    fun showNews(news: List<NewsViewModel>)
}