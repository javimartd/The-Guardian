package com.javimartd.theguardian.data.factory

import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResultsModel
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResponseModel
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.javimartd.theguardian.domain.model.News

object NewsFactory {

    fun makeNews(): News {
        return News(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                false,
                DataFactory.randomString(),
                DataFactory.randomString())
    }

    fun makeNewsDataModel(): NewsDataModel {
        return NewsDataModel(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                false,
                DataFactory.randomString(),
                DataFactory.randomString())
    }

    fun makeNewsResponseModel(): NewsResponseModel {
        return NewsResponseModel(NewsResultsModel())
    }
}