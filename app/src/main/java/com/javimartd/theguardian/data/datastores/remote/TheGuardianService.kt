package com.javimartd.theguardian.data.datastores.remote

import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResponseModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheGuardianService {

    @GET("/search")
    fun getNews(@Query("show-fields") showFields: String,
                @Query("api-key") apiKey: String): Observable<NewsResponseModel>
}