package com.javimartd.theguardian.data.datastores

import com.javimartd.theguardian.data.model.news.NewsDataModel
import io.reactivex.Single

interface NewsDataStore {
    fun getNews(): Single<List<NewsDataModel>>
    fun saveNews(news: NewsDataModel)
}