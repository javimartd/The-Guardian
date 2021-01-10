package com.javimartd.theguardian.data.datastores.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.javimartd.theguardian.data.datastores.local.db.AppDatabase
import com.javimartd.theguardian.data.datastores.local.db.news.NewsDao
import com.javimartd.theguardian.factory.NewsFactory
import org.junit.After
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
        val context = InstrumentationRegistry.getInstrumentation().context
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
        val testObserver = newsDao.getAll().test()
        testObserver.assertValue { it.size == fakeNews.size }
        testObserver.assertValue(fakeNews)
    }

    @Test
    @Throws(Exception::class)
    fun add_news_and_find_by_section() {
        val fakeNews = NewsFactory.makeNewsEntity(1)
        val newsEntity = fakeNews[0]
        newsEntity.sectionId = "opinion"
        newsDao.insert(fakeNews)
        val testObserver = newsDao.findBySection("opinion").test()
        testObserver.assertValue { it[0] == newsEntity }
    }

    @Test
    @Throws(Exception::class)
    fun delete_news_completes() {
        val fakeNews = NewsFactory.makeNewsEntity(1)
        newsDao.insert(fakeNews)
        val testObserver = newsDao.deleteAll().test()
        testObserver.assertComplete()
    }

    @Test
    @Throws(Exception::class)
    fun add_news_and_empty_database_and_check_database_is_empty() {
        val fakeNews = NewsFactory.makeNewsEntity(3)
        newsDao.insert(fakeNews)
        newsDao.deleteAll().doOnComplete {
            val testObserver = newsDao.getAll().test()
            testObserver.assertEmpty()
        }
    }
}