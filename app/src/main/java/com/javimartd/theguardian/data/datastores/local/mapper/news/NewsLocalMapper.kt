package com.javimartd.theguardian.data.datastores.local.mapper.news

import com.javimartd.theguardian.data.datastores.local.db.news.NewsEntity
import com.javimartd.theguardian.data.datastores.local.mapper.LocalMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import javax.inject.Inject

class NewsLocalMapper @Inject constructor(): LocalMapper<List<NewsEntity>, List<NewsDataModel>> {

    override fun mapFromLocal(entity: List<NewsEntity>): List<NewsDataModel> {
        return entity.map { mapFromLocal(it) }
    }

    override fun mapToLocal(data: List<NewsDataModel>): List<NewsEntity> {
        return data.map { mapToLocal(it) }
    }

    private fun mapFromLocal(entity: NewsEntity): NewsDataModel {
        return NewsDataModel(entity.id,
                entity.sectionId,
                entity.sectionName,
                entity.webTitle,
                entity.date,
                entity.webUrl,
                true,
                entity.thumbnail,
                entity.description)
    }

    private fun mapToLocal(data: NewsDataModel): NewsEntity {
        return NewsEntity(data.id,
                data.sectionId,
                data.sectionName,
                data.title,
                data.description,
                data.date,
                data.webUrl,
                data.thumbnail)
    }
}