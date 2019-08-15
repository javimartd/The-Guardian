package com.javimartd.theguardian.ui.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UrlModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun providesBaseUrl(): String {
        return "https://content.guardianapis.com"
    }
}