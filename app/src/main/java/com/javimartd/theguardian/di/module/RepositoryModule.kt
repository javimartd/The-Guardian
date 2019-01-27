package com.javimartd.theguardian.di.module

import com.javimartd.theguardian.data.NewsDataRepository
import com.javimartd.theguardian.data.api.APIService
import com.javimartd.theguardian.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesNewsRepository(apiService: APIService): NewsRepository {
        return NewsDataRepository(apiService)
    }
}