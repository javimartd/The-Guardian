package com.javimartd.theguardian.di.module

import com.javimartd.theguardian.data.NewsDataRepository
import com.javimartd.theguardian.data.api.APIService
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.framework.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesNewsRepository(apiService: APIService,
                               appDatabase: AppDatabase): NewsRepository {
        return NewsDataRepository(apiService)
    }
}