package com.javimartd.theguardian.data.mapper.news

import com.javimartd.theguardian.data.mapper.DataMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.javimartd.theguardian.domain.model.News
import javax.inject.Inject

class NewsDataMapper @Inject constructor(): DataMapper<List<NewsDataModel>, List<News>> {

    override fun mapFromData(data: List<NewsDataModel>): List<News> {
        return data.map { mapFromData(it) }
    }

    override fun mapToData(domain: List<News>): List<NewsDataModel> {
        return domain.map { mapToData(it) }
    }

    private fun mapFromData(model: NewsDataModel): News {
        return News(model.sectionId,
                model.sectionName,
                model.title,
                model.date,
                model.webUrl,
                model.live,
                model.thumbnail,
                model.description)
    }

    private fun mapToData(domain: News): NewsDataModel {
        return NewsDataModel(domain.sectionId,
                domain.sectionName,
                domain.title,
                domain.date,
                domain.webUrl,
                domain.live,
                domain.thumbnail,
                domain.description)
    }
}