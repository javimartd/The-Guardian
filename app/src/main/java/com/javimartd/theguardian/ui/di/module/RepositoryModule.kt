package com.javimartd.theguardian.ui.di.module

import android.app.Application
import android.content.Context
import com.javimartd.theguardian.data.NewsRepositoryImpl
import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.datastores.local.NewsLocalDataStore
import com.javimartd.theguardian.data.datastores.local.db.AppDatabase
import com.javimartd.theguardian.data.datastores.local.mapper.news.NewsLocalMapper
import com.javimartd.theguardian.data.datastores.memory.NewsMemoryDataStore
import com.javimartd.theguardian.data.datastores.remote.NewsRemoteDataStore
import com.javimartd.theguardian.data.datastores.remote.TheGuardianService
import com.javimartd.theguardian.data.datastores.remote.mapper.news.NewsRemoteMapper
import com.javimartd.theguardian.data.mapper.news.NewsDataMapper
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
    @Singleton
    fun providesNewsRemoteDataStore(theGuardianService: TheGuardianService,
                                    newsRemoteMapper: NewsRemoteMapper): NewsDataStore {
        return NewsRemoteDataStore(theGuardianService, newsRemoteMapper)
    }

    @Provides
    @Singleton
    fun providesNewsMemoryDataStore(): NewsDataStore {
        return NewsMemoryDataStore()
    }

    @Provides
    @Singleton
    fun providesNewsLocalDataStore(appDatabase: AppDatabase,
                                   newsLocalMapper: NewsLocalMapper): NewsDataStore {
        return NewsLocalDataStore(appDatabase, newsLocalMapper)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsRemoteDataStore: NewsRemoteDataStore,
                               newsMemoryDataStore: NewsMemoryDataStore,
                               newsLocalDataStore: NewsLocalDataStore,
                               newsDataMapper: NewsDataMapper): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataStore, newsMemoryDataStore, newsLocalDataStore, newsDataMapper)
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