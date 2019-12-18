package com.javimartd.theguardian.data.datastores

import com.javimartd.theguardian.data.model.news.NewsDataModel
import io.reactivex.Observable

interface NewsDataStore {
    fun getNews(): Observable<List<NewsDataModel>>
    fun saveNews(news: NewsDataModel)
}