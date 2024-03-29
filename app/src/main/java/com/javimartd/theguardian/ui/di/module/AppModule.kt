package com.javimartd.theguardian.ui.di.module

import androidx.room.Room
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.data.datastores.local.db.AppDatabase
import com.javimartd.theguardian.ui.common.BaseWebViewClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    private val DATABASE_NAME = "the_guardian_db"

    @Singleton
    @Provides
    fun providesDatabase(): AppDatabase {
        return Room.databaseBuilder(
            TheGuardianApplication.instance,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesBaseWebViewClient(): BaseWebViewClient {
        return BaseWebViewClient()
    }
}