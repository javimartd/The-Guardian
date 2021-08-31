package com.javimartd.theguardian.ui.di.module

import com.javimartd.theguardian.BuildConfig
import com.javimartd.theguardian.data.datastores.remote.TheGuardianService
import com.javimartd.theguardian.data.datastores.remote.interceptors.MockInterceptor
import com.javimartd.theguardian.data.datastores.remote.interceptors.NewsAuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("MockInterceptor") interceptor: Interceptor): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE
        })
        //.addInterceptor(interceptor)
        .addInterceptor(NewsAuthInterceptor())
        clientBuilder.readTimeout(20, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient,
                              @Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TheGuardianService {
        return retrofit.create(TheGuardianService::class.java)
    }

    @Provides
    @Singleton
    @Named("MockInterceptor")
    fun provideMockInterceptor(): Interceptor = MockInterceptor()
}