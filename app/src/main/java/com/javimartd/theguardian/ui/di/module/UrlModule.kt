package com.javimartd.theguardian.ui.di.module

import com.javimartd.theguardian.R
import com.javimartd.theguardian.TheGuardianApplication
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
        return TheGuardianApplication.instance.getString(R.string.HOST)
    }
}