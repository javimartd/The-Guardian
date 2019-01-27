package com.javimartd.theguardian.framework.db

import android.arch.persistence.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Delete
    fun delete(newsEntity: NewsEntity)
}