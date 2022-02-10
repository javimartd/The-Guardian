package com.javimartd.theguardian.data.datastores.remote.interceptors

import com.javimartd.theguardian.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsAuthInterceptor(private val apiKey: String = BuildConfig.THE_GUARDIAN_API_KEY) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("api-key", apiKey)
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}