package com.javimartd.theguardian.ui.di.module

import android.app.Application
import android.content.Context
import com.javimartd.theguardian.data.NewsRepositoryImpl
import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.local.NewsLocalDataStore
import com.javimartd.theguardian.data.datastores.remote.APIService
import com.javimartd.theguardian.data.datastores.remote.NewsRemoteDataStore
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.domain.repositories.ResourceRepository
import com.javimartd.theguardian.framework.db.AppDatabase
import com.javimartd.theguardian.framework.resources.ResourceRepositoryImpl
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
    fun providesNewsRepository(newsRemoteDataStore: NewsRemoteDataStore,
                               newsLocalDataStore: NewsLocalDataStore): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataStore, newsLocalDataStore)
    }

    @Provides
    fun providesNewsRemoteDataStore(apiService: APIService): NewsDataStore {
        return NewsRemoteDataStore(apiService)
    }

    @Provides
    fun providesNewsLocalDataStore(appDatabase: AppDatabase): NewsDataStore {
        return NewsLocalDataStore(appDatabase)
    }

    @Singleton
    @Provides
    internal fun providesResourceRepository(context: Context): ResourceRepository {
        return ResourceRepositoryImpl(context.resources)
    }
}