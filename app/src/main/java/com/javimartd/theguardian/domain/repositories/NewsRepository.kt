package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.entities.News

interface NewsRepository {
    fun getNews(getLocalNews: Boolean): List<News>
    fun saveNews(news: News)
}