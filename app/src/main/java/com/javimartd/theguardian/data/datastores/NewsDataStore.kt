package com.javimartd.theguardian.data.datastores

import com.javimartd.theguardian.data.model.news.NewsModel
import io.reactivex.Observable

interface NewsDataStore {
    fun getNews(): Observable<List<NewsModel>>
    fun saveNews(news: NewsModel)
}