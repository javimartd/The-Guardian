package com.javimartd.theguardian.data.datastores.local

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.extensions.toDomain
import com.javimartd.theguardian.data.extensions.toEntity
import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.framework.db.AppDatabase
import javax.inject.Inject

class NewsLocalDataStore @Inject constructor(private val dataBase: AppDatabase): NewsDataStore {

    override fun getNews(): List<News> {
        return dataBase.newsDao().getAll().toDomain()
    }

    override fun saveNews(news: News) {
        dataBase.newsDao().insert(news.toEntity())
    }
}