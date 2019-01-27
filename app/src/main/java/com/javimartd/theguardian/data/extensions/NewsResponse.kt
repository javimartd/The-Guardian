package com.javimartd.theguardian.data.extensions

import com.javimartd.theguardian.framework.db.NewsEntity
import com.javimartd.theguardian.data.api.response.news.ArticleData
import com.javimartd.theguardian.data.api.response.news.NewsResponse
import com.javimartd.theguardian.domain.model.News

fun NewsResponse.toDomain(): List<News> {
    return newsResponse.results.map { articleToDomain(it) }
}

fun List<News>.toEntity(): List<NewsEntity> {
    return map { newsToEntity(it) }
}

fun NewsResponse.toEntity(): List<NewsEntity> {
    return newsResponse.results.map { newsToEntity(it) }
}

private fun articleToDomain(it: ArticleData): News {
    return News(it.sectionId,
            it.sectionName,
            it.webTitle,
            it.webPublicationDate,
            it.webUrl,
            (it.fields.liveBloggingNow == "true"),
            it.fields.thumbnail)
}

private fun newsToEntity(it: News): NewsEntity {
    return NewsEntity(sectionName = it.sectionName,
            date = it.date,
            webTitle = it.title,
            webUrl = it.webUrl,
            thumbnail = it.thumbnail,
            uid = 0)
}


private fun newsToEntity(it: ArticleData): NewsEntity {
    return NewsEntity(sectionName = it.sectionName,
            date = it.webPublicationDate,
            webTitle = it.webTitle,
            webUrl = it.webUrl,
            thumbnail = it.fields.thumbnail,
            uid = 0)
}