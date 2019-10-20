package com.javimartd.theguardian.ui.di.module

import android.app.Application
import android.content.Context
import com.javimartd.theguardian.data.NewsRepositoryImpl
import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.remote.NewsRemoteDataStore
import com.javimartd.theguardian.data.datastores.remote.TheGuardianService
import com.javimartd.theguardian.data.datastores.remote.mapper.news.NewsRemoteMapper
import com.javimartd.theguardian.data.mapper.news.NewsModelMapper
import com.javimartd.theguardian.domain.executor.PostExecutionThread
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.domain.repositories.ResourceRepository
import com.javimartd.theguardian.framework.resources.ResourceRepositoryImpl
import com.javimartd.theguardian.ui.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    private lateinit var context: Context

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        this.context = application.applicationContext
        return context
    }

    @Provides
    fun providesNewsRemoteDataStore(theGuardianService: TheGuardianService,
                                    newsRemoteMapper: NewsRemoteMapper): NewsDataStore {
        return NewsRemoteDataStore(theGuardianService, newsRemoteMapper)
    }

    @Provides
    fun providesNewsRepository(newsRemoteDataStore: NewsRemoteDataStore,
                               newsModelMapper: NewsModelMapper): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataStore, newsModelMapper)
    }

    @Provides
    fun providesResourceRepository(context: Context): ResourceRepository {
        return ResourceRepositoryImpl(context.resources)
    }

    @Provides
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}