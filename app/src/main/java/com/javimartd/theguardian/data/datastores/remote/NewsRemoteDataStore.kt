package com.javimartd.theguardian.data.datastores.remote

import com.javimartd.theguardian.BuildConfig
import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.remote.mapper.news.NewsRemoteMapper
import com.javimartd.theguardian.data.model.news.NewsModel
import io.reactivex.Observable
import javax.inject.Inject


class NewsRemoteDataStore @Inject constructor(private val service: TheGuardianService,
                                              private val mapper: NewsRemoteMapper)
    : NewsDataStore {

    override fun getNews(): Observable<List<NewsModel>> {
        return service.getNews("all", BuildConfig.THE_GUARDIAN_API_KEY)
                .map {
                    mapper.mapFromRemote(it.newsResponse)
                }
    }

    override fun saveNews(news: NewsModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}