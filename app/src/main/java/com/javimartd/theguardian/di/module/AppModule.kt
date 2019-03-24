package com.javimartd.theguardian.di.module

import androidx.room.Room
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.framework.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    private val DATABASE_NAME = "the_guardian_db"

    @Singleton
    @Provides
    internal fun providesDatabase(): AppDatabase {
        return Room.databaseBuilder(TheGuardianApplication.instance, AppDatabase::class.java, DATABASE_NAME).build()
    }
}