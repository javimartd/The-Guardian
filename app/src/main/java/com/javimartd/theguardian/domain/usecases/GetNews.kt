package com.javimartd.theguardian.domain.usecases

import com.javimartd.theguardian.domain.common.UseCase
import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(private val repository: NewsRepository): UseCase() { //: Command<DailyNews>

    @Throws(Exception::class)
    override suspend fun execution(): List<News> {
        return repository.getNews()
    }
}