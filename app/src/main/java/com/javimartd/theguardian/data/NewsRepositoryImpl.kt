package com.javimartd.theguardian.data

import com.javimartd.theguardian.data.common.BaseRemote
import com.javimartd.theguardian.data.datastores.local.NewsLocalDataStore
import com.javimartd.theguardian.data.datastores.remote.NewsRemoteDataStore
import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.domain.repositories.NewsRepository

class NewsRepositoryImpl(private val remoteDataStore: NewsRemoteDataStore,
                         private val localDataStore: NewsLocalDataStore): BaseRemote(), NewsRepository {

    override fun getNews(getLocalNews: Boolean): List<News> {
        return if (getLocalNews) {
            localDataStore.getNews()
        } else {
            remoteDataStore.getNews()
        }
    }

    override fun saveNews(news: News) {
        localDataStore.saveNews(news)
    }
}
