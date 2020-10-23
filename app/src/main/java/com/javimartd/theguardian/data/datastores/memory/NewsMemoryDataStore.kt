package com.javimartd.theguardian.data.datastores.memory

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.model.news.NewsDataModel
import io.reactivex.Single
import javax.inject.Inject

class NewsMemoryDataStore @Inject constructor(): NewsDataStore {

    private var news = mutableListOf<NewsDataModel>()

    override fun getNewsFromMemory(): Single<List<NewsDataModel>> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(news)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun getNewsFromLocal(): Single<List<NewsDataModel>> {
        throw UnsupportedOperationException("Getting news from local isn't supported here...")
    }

    override fun getNewsFromNetwork(): Single<List<NewsDataModel>> {
        throw UnsupportedOperationException("Getting news from network isn't supported here...")
    }

    override fun saveNewsInMemory(data: List<NewsDataModel>) {
        this.news.clear()
        this.news.addAll(data)
    }

    override fun saveNewsInLocal(data: List<NewsDataModel>) {
        throw UnsupportedOperationException("Saving news in local isn't supported here...")
    }
}