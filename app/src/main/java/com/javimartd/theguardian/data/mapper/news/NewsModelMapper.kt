package com.javimartd.theguardian.data.mapper.news

import com.javimartd.theguardian.data.mapper.ModelMapper
import com.javimartd.theguardian.data.model.news.NewsModel
import com.javimartd.theguardian.domain.model.News
import javax.inject.Inject

class NewsModelMapper @Inject constructor(): ModelMapper<List<NewsModel>, List<News>> {

    override fun mapFromModel(model: List<NewsModel>): List<News> {
        return model.map { mapFromModel(it) }
    }

    override fun mapToModel(domain: List<News>): List<NewsModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapFromModel(model: NewsModel): News {
        return News(model.sectionId,
                model.sectionName,
                model.title,
                model.date,
                model.webUrl,
                model.live,
                model.thumbnail,
                model.description)
    }
}