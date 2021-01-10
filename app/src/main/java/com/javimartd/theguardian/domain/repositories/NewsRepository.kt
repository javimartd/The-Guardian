package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.model.News
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsRepository {
    fun getNews(): Single<List<News>>
    fun saveNews(news: News): Completable
}