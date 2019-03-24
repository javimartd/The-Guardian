package com.javimartd.theguardian.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Query("DELETE FROM news")
    fun deleteAll()

    @Query("SELECT * FROM news WHERE section == :sectionID")
    fun findBySection(sectionID: String): List<NewsEntity>

}