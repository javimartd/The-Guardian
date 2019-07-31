package com.javimartd.theguardian.di.module

import android.app.Application
import android.content.Context
import com.javimartd.theguardian.data.NewsRepositoryImpl
import com.javimartd.theguardian.data.datasources.api.APIService
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.domain.repositories.ResourceRepository
import com.javimartd.theguardian.framework.db.AppDatabase
import com.javimartd.theguardian.framework.resources.ResourceDataRepository
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
    fun providesNewsRepository(apiService: APIService,
                               appDatabase: AppDatabase): NewsRepository {
        return NewsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    internal fun providesResourceRepository(context: Context): ResourceRepository {
        return ResourceDataRepository(context.resources)
    }
}