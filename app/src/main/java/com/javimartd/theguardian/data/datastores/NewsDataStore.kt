package com.javimartd.theguardian.data.datastores

import com.javimartd.theguardian.data.model.news.NewsDataModel
import io.reactivex.Single

interface NewsDataStore {
    fun getNewsFromNetwork(): Single<List<NewsDataModel>>
    fun getNewsFromMemory(): Single<List<NewsDataModel>>
    fun getNewsFromLocal(): Single<List<NewsDataModel>>
    fun saveNewsInMemory(data: List<NewsDataModel>)
    fun saveNewsInLocal(data: List<NewsDataModel>)
}