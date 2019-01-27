package com.javimartd.theguardian.data

import android.util.Log
import java.net.URL

/**
 * readText: extension function from the Kotlin standard library
 * to perform a simple API request.
 * Not recommended for huge responses.
 */
class Request (private val url : String) {

    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }

    /*companion object {
        private const val URL_NEWS = "https://content.guardianapis.com/search?show-fields=all&api-key=" + BuildConfig.THE_GUARDIAN_API_KEY
    }

    override fun getNews(): List<News> {
        val responseJson = URL(URL_NEWS).readText()
        val response: NewsResponse =  Gson().fromJson(responseJson, NewsResponse::class.java)
        return response.response.toDomain()
    }*/
}