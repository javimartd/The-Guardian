package com.javimartd.theguardian.data

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.mapper.news.NewsModelMapper
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import io.reactivex.Observable

class NewsRepositoryImpl(private val remoteDataStore: NewsDataStore,
                         private val mapper: NewsModelMapper): NewsRepository {

    override fun getNews(): Observable<List<News>> {
        return remoteDataStore.getNews().map { mapper.mapFromModel(it) }
    }

    override fun saveNews(news: News) {
        // does not require implementation at this moment
    }
}
