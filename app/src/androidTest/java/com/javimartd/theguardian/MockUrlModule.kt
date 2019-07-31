package com.javimartd.theguardian

import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockUrlModule {

    private val mockWebServer = MockWebServer()

    @Provides
    @Singleton
    @Named("baseUrl")
    fun providesBaseUrl(): String {
        return mockWebServer.url("/").toString()
    }
}