package com.javimartd.theguardian.data

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.javimartd.theguardian.framework.db.AppDatabase
import com.javimartd.theguardian.framework.db.NewsDao
import com.javimartd.theguardian.framework.db.NewsEntity
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NewsEntityReadWriteTest {

    private lateinit var newsDao: NewsDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        newsDao = db.newsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val news: MutableList<NewsEntity> = mutableListOf()
        val newsEntity = NewsEntity(1,
                "Opinion",
                "Title",
                "02-02-2018",
                "",
                "")

        news.add(newsEntity)
        newsDao.insert(news)
        val newsByOpinion = newsDao.findBySection("Opinion")
        assertThat(newsByOpinion[0], equalTo(newsEntity))
    }

}