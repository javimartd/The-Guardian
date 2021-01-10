package com.javimartd.theguardian.data.datastores.local.db.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Single<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Query("DELETE FROM news")
    fun deleteAll(): Completable

    @Query("SELECT * FROM news WHERE id == :id")
    fun findById(id: String): Single<List<NewsEntity>>

    @Query("DELETE FROM news WHERE id == :id")
    fun deleteById(id: String)

    @Query("SELECT * FROM news WHERE sectionId == :sectionId")
    fun findBySection(sectionId: String): Single<List<NewsEntity>>

    @Query("SELECT COUNT(*) from news WHERE id == :id")
    fun countById(id: String): Single<Int>

}