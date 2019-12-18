package com.javimartd.theguardian.data.datastores.local

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.javimartd.theguardian.data.datastores.local.db.AppDatabase
import com.javimartd.theguardian.data.datastores.local.db.news.NewsDao
import com.javimartd.theguardian.factory.NewsFactory
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest {

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
    fun add_news_and_get_all_news() {
        val fakeNews = NewsFactory.makeNewsEntity(1)
        newsDao.insert(fakeNews)
        val localNews = newsDao.getAll()
        Assert.assertEquals(fakeNews.size, localNews.size)
    }

    @Test
    @Throws(Exception::class)
    fun add_news_and_find_by_section() {
        val fakeNews = NewsFactory.makeNewsEntity(1)
        val newsEntity = fakeNews[0]
        newsEntity.sectionId = "opinion"
        newsDao.insert(fakeNews)
        val newsByOpinion = newsDao.findBySection("opinion")
        assertThat(newsByOpinion[0], equalTo(newsEntity))
    }

    @Test
    @Throws(Exception::class)
    fun add_news_and_empty_database_and_check_database_is_empty() {
        val fakeNews = NewsFactory.makeNewsEntity(1)
        newsDao.insert(fakeNews)
        newsDao.deleteAll()
        assertTrue(newsDao.getAll().isEmpty())
    }
}