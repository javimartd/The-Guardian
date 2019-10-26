package com.javimartd.theguardian.ui.di.modules

import android.app.Application
import android.content.Context
import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.domain.executor.PostExecutionThread
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.domain.repositories.ResourceRepository
import com.javimartd.theguardian.framework.resources.ResourceRepositoryImpl
import com.javimartd.theguardian.ui.UiThread
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestRepositoryModule {

    private lateinit var context: Context

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        this.context = application.applicationContext
        return context
    }

    @Provides
    @Singleton
    fun providesNewsRemoteDataStore(): NewsDataStore {
        return mock()
    }

    @Provides
    @Singleton
    fun providesNewsRepository(): NewsRepository {
        return mock()
    }

    @Provides
    @Singleton
    fun providesResourceRepository(context: Context): ResourceRepository {
        return ResourceRepositoryImpl(context.resources)
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }


}