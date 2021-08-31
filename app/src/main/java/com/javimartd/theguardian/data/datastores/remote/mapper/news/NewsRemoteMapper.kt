package com.javimartd.theguardian.data.datastores.remote.mapper.news

import com.javimartd.theguardian.data.datastores.remote.mapper.RemoteMapper
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsRemoteModel
import com.javimartd.theguardian.data.model.news.NewsDataModel
import javax.inject.Inject

class NewsRemoteMapper @Inject constructor(): RemoteMapper<List<NewsRemoteModel>, List<NewsDataModel>> {

    override fun mapFromRemote(remote: List<NewsRemoteModel>?): List<NewsDataModel> {
        return remote?.let { list ->
            list.map { mapFromRemote(it) }
        } ?: emptyList()
    }

    private fun mapFromRemote(remote: NewsRemoteModel): NewsDataModel {
        return NewsDataModel(remote.id,
                remote.sectionId,
                remote.sectionName,
                remote.webTitle,
                remote.webPublicationDate,
                remote.webUrl,
                remote.fields?.liveBloggingNow == "true",
                remote.fields?.thumbnail ?: "",
                remote.fields?.bodyText ?: "")
    }
}