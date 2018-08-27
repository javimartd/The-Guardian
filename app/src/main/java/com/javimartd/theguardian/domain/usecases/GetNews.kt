package com.javimartd.theguardian.domain.usecases

import com.javimartd.theguardian.data.NewsRepositoryImpl
import com.javimartd.theguardian.data.mapper.DataMapper
import com.javimartd.theguardian.domain.Command
import com.javimartd.theguardian.domain.model.DailyNews

class GetNews : Command<DailyNews> {

    override fun execute(): DailyNews {
        val newsRequest = NewsRepositoryImpl()
        return DataMapper().convertFromDataModel(newsRequest.getNews())
    }
}