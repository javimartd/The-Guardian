package com.javimartd.theguardian.data.datastores.remote

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.remote.mapper.news.NewsRemoteMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import io.reactivex.Single
import javax.inject.Inject


class NewsRemoteDataStore @Inject constructor(private val service: TheGuardianService,
                                              private val mapper: NewsRemoteMapper)
    : NewsDataStore {

    override fun getNewsFromNetwork(): Single<List<NewsDataModel>> {
        return service
            .getNews("all")
            .map {
                mapper.mapFromRemote(it.newsResults?.results)
            }
    }

    override fun getNewsFromMemory(): Single<List<NewsDataModel>> {
        throw UnsupportedOperationException("Getting news from memory isn't supported here...")
    }

    override fun getNewsFromLocal(): Single<List<NewsDataModel>> {
        throw UnsupportedOperationException("Getting news from local isn't supported here...")
    }

    override fun saveNewsInMemory(data: List<NewsDataModel>) {
        throw UnsupportedOperationException("Saving news isn't supported here...")
    }

    override fun saveNewsInLocal(data: List<NewsDataModel>) {
        throw UnsupportedOperationException("Saving news in local isn't supported here...")
    }
}