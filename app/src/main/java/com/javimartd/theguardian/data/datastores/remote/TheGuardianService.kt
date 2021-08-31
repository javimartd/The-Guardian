package com.javimartd.theguardian.data.datastores.remote

import com.javimartd.theguardian.data.datastores.remote.model.news.NewsApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TheGuardianService {

    @GET("/search")
    fun getNews(
        @Query("show-fields") showFields: String
    ): Single<NewsApiResponse>
}