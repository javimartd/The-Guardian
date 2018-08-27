package com.javimartd.theguardian.data

import com.google.gson.Gson
import com.javimartd.theguardian.BuildConfig
import com.javimartd.theguardian.data.response.news.Response
import com.javimartd.theguardian.domain.repositories.NewsRepository
import java.net.URL

class NewsRepositoryImpl : NewsRepository {

    companion object {
        private const val URL_NEWS = "https://content.guardianapis.com/search?show-fields=all&api-key=" + BuildConfig.THE_GUARDIAN_API_KEY
    }

    override fun getNews() : Response {
        val responseJson = URL(URL_NEWS).readText()
        return Gson().fromJson(responseJson, Response::class.java)
    }
}