package com.javimartd.theguardian.data.datastores.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javimartd.theguardian.data.datastores.local.db.news.NewsDao
import com.javimartd.theguardian.data.datastores.local.db.news.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}