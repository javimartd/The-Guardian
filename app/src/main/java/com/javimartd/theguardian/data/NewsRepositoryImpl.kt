package com.javimartd.theguardian.data

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.mapper.DataMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val remoteDataStore: NewsDataStore,
                                             private val memoryDataStore: NewsDataStore,
                                             private val localDataStore: NewsDataStore,
                                             private val mapper: DataMapper<List<NewsDataModel>, List<News>>)
    : NewsRepository {

    override fun getNews(): Single<List<News>> {
        return remoteDataStore.getNewsFromNetwork()
                .doOnSuccess {
                    memoryDataStore.saveNewsInMemory(it)
                }
                .map {
                    mapper.mapFromData(it)
                }
    }

    override fun saveNews(news: News): Completable {
        TODO("Not yet implemented")
    }
}
