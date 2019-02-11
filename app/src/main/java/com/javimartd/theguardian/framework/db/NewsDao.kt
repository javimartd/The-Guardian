package com.javimartd.theguardian.framework.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

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