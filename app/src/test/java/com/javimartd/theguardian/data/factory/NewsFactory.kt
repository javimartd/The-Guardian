package com.javimartd.theguardian.data.factory

import com.javimartd.theguardian.data.datastores.remote.model.news.NewsApiResponse
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResultsRemoteModel
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.javimartd.theguardian.domain.model.News

object NewsFactory {

    fun makeNews(count: Int): List<News> {
        val news = mutableListOf<News>()
        repeat(count) {
            news.add(makeNews())
        }
        return news
    }

    fun makeNews(): News {
        return News(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                false,
                DataFactory.randomString(),
                DataFactory.randomString()
        )
    }

    fun makeNewsDataModel(): NewsDataModel {
        return NewsDataModel(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                false,
                DataFactory.randomString(),
                DataFactory.randomString()
        )
    }

    fun makeNewsApiResponse(): NewsApiResponse {
        return NewsApiResponse(
            NewsResultsRemoteModel(
                "ok",
                emptyList()
            )
        )
    }
}