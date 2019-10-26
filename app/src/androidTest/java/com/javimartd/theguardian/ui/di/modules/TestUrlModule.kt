package com.javimartd.theguardian.ui.di.modules

import com.javimartd.theguardian.R
import com.javimartd.theguardian.TestTheGuardianApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestUrlModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun providesBaseUrl(): String {
        return TestTheGuardianApplication.instance.getString(R.string.HOST)
    }
}