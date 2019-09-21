package com.javimartd.theguardian.data.extensions

import com.javimartd.theguardian.data.datastores.remote.model.news.ArticleData
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResponse
import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.framework.db.NewsEntity

fun NewsResponse.toDomain(): List<News> {
    return newsResponse.results.map { articleToDomain(it) }
}

private fun articleToDomain(it: ArticleData): News {
    return News(it.sectionId,
            it.sectionName,
            it.webTitle,
            it.webPublicationDate,
            it.webUrl,
            it.fields.liveBloggingNow == "true",
            it.fields.thumbnail,
            it.fields.bodyText)
}

fun List<NewsEntity>.toDomain(): List<News> {
    return map { entityToNews(it) }
}

private fun entityToNews(it: NewsEntity): News {
    return News(it.sectionId,
            it.sectionName,
            it.webTitle,
            it.date,
            it.webUrl,
            false,
            it.thumbnail,
            it.description)
}

fun News.toEntity(): List<NewsEntity> {
    val entityNews = mutableListOf<NewsEntity>()
    entityNews.add(NewsEntity(sectionName = sectionName,
            date = date,
            webTitle = title,
            webUrl = webUrl,
            thumbnail = thumbnail,
            id = 0,
            sectionId = sectionId,
            description = description))
    return entityNews
}