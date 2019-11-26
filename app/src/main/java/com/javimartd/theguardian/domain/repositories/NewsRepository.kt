package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.model.News
import io.reactivex.Completable
import io.reactivex.Observable

interface NewsRepository {
    fun getNews(): Observable<List<News>>
    fun saveNews(news: News): Completable
}