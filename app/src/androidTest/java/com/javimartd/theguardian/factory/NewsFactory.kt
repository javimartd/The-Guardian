package com.javimartd.theguardian.factory

import com.javimartd.theguardian.data.datastores.local.db.news.NewsEntity
import com.javimartd.theguardian.domain.model.News

object NewsFactory {

    fun makeNews(count: Int): List<News> {
        val news = mutableListOf<News>()
        repeat(count) {
            news.add(createNews())
        }
        return news
    }

    fun makeNewsEntity(count: Int): List<NewsEntity> {
        val news = mutableListOf<NewsEntity>()
        repeat(count) {
            news.add(createNewsEntity())
        }
        return news
    }

    private fun createNews(): News {
        return News(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                "2019-10-26T13:02:39Z",
                DataFactory.randomString(),
                DataFactory.randomBoolean(),
                DataFactory.randomString(),
                DataFactory.randomString())
    }

    private fun createNewsEntity(): NewsEntity {
        return NewsEntity(
                DataFactory.randomInt(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString())
    }
}