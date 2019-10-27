package com.javimartd.theguardian.data

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.mapper.news.NewsDataMapper
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val remoteDataStore: NewsDataStore,
                                             private val mapper: NewsDataMapper)
    : NewsRepository {

    override fun getNews(): Observable<List<News>> {
        return remoteDataStore.getNews().map { mapper.mapFromData(it) }
    }

    override fun saveNews(news: News) {
        TODO("not implemented")
    }
}
