package com.javimartd.theguardian.data.mapper

import com.javimartd.theguardian.data.response.news.ArticleResponse
import com.javimartd.theguardian.data.response.news.Response
import com.javimartd.theguardian.domain.model.DailyNews
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.presentation.news.factory.SectionFactory

class DataMapper {

    fun convertFromDataModel(data: Response): DailyNews {
        return DailyNews(convertResponseToDomain(data.response.results))
    }

    private fun convertResponseToDomain(list: List<ArticleResponse>): List<News> {
        return list.map { convertNewsItemToDomain(it) }
    }

    private fun convertNewsItemToDomain(it: ArticleResponse): News {
        return News(SectionFactory.getSection(it.sectionId, it.sectionName),
                it.webTitle,
                it.webPublicationDate,
                it.webUrl,
                it.fields.liveBloggingNow?.equals("true") ?: false,
                it.fields.thumbnail ?: "")
    }
}