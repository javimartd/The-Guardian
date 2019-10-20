package com.javimartd.theguardian.data.datastores.remote.mapper.news

import com.javimartd.theguardian.data.datastores.remote.mapper.RemoteMapper
import com.javimartd.theguardian.data.datastores.remote.model.news.ArticleRemoteModel
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsRemoteModel
import com.javimartd.theguardian.data.model.news.NewsModel
import javax.inject.Inject

class NewsRemoteMapper @Inject constructor(): RemoteMapper<NewsRemoteModel, List<NewsModel>> {

    override fun mapFromRemote(remoteModel: NewsRemoteModel): List<NewsModel> {
        return remoteModel.results.map { example(it) }
    }

    private fun example(articleModel: ArticleRemoteModel): NewsModel {
        return NewsModel(articleModel.sectionId,
                articleModel.sectionName,
                articleModel.webTitle,
                articleModel.webPublicationDate,
                articleModel.webUrl,
                articleModel.fields.liveBloggingNow == "true",
                articleModel.fields.thumbnail,
                articleModel.fields.bodyText)
    }
}