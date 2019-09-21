package com.javimartd.theguardian.data.datastores.remote

import com.javimartd.theguardian.BuildConfig
import com.javimartd.theguardian.data.common.BaseRemote
import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResponse
import com.javimartd.theguardian.data.extensions.toDomain
import com.javimartd.theguardian.domain.entities.News
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataStore @Inject constructor(private val apiService: APIService): NewsDataStore,
        BaseRemote() {

    override fun getNews(): List<News> {
        val serverResponse: Response<*> = executeCall(
                apiService.getNews("all", BuildConfig.THE_GUARDIAN_API_KEY))
        val response = serverResponse.body() as NewsResponse
        return response.toDomain()
    }

    override fun saveNews(news: News) {
        // does not require implementation
    }
}