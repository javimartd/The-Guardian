package com.javimartd.theguardian.data.datastores.local

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.local.db.AppDatabase
import com.javimartd.theguardian.data.datastores.local.mapper.news.NewsLocalMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import io.reactivex.Single
import javax.inject.Inject

class NewsLocalDataStore @Inject constructor(private val appDatabase: AppDatabase,
                                             private val mapper: NewsLocalMapper)
    : NewsDataStore {

    override fun getNewsFromNetwork(): Single<List<NewsDataModel>> {
        throw UnsupportedOperationException("Getting news from network isn't supported here...")
    }

    override fun getNewsFromMemory(): Single<List<NewsDataModel>> {
        throw UnsupportedOperationException("Getting news from memory isn't supported here...")
    }

    override fun getNewsFromLocal(): Single<List<NewsDataModel>> {
        return appDatabase.newsDao().getAll()
                .map { mapper.mapFromLocal(it) }
    }

    override fun saveNewsInMemory(data: List<NewsDataModel>) {
        throw UnsupportedOperationException("Saving news isn't supported here...")
    }

    override fun saveNewsInLocal(data: List<NewsDataModel>) {
        return appDatabase.newsDao().insert(mapper.mapToLocal(data))
    }
}