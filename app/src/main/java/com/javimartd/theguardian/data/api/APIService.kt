package com.javimartd.theguardian.data.api

import com.javimartd.theguardian.data.api.response.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/search")
    fun getNews(@Query("show-fields") showFields: String,
                @Query("api-key") apiKey: String): Call<NewsResponse>
}