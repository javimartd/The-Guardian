package com.javimartd.theguardian.data

import com.javimartd.theguardian.BuildConfig
import com.javimartd.theguardian.data.api.APIService
import com.javimartd.theguardian.data.api.response.news.NewsResponse
import com.javimartd.theguardian.data.common.Repository
import com.javimartd.theguardian.data.extensions.toDomain
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import retrofit2.Response

class NewsDataRepository(private val apiService: APIService): NewsRepository, Repository() {

    @Throws(Exception::class)
    override fun getNews(): List<News> {
        val serverResponse: Response<*> = executeCall(apiService.getNews("all",
                BuildConfig.THE_GUARDIAN_API_KEY))
        val response = serverResponse.body() as NewsResponse
        return response.toDomain()
    }
}