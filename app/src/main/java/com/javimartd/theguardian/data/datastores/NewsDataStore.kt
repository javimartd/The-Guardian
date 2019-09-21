package com.javimartd.theguardian.data.datastores

import com.javimartd.theguardian.domain.entities.News

interface NewsDataStore {
    fun getNews(): List<News>
    fun saveNews(news: News)
}