package com.javimartd.theguardian.domain.usecases

import com.javimartd.theguardian.domain.common.UseCase
import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository): UseCase() { //: Command<DailyNews>

    override suspend fun execution(): List<News> {
        return repository.getNews(false)
    }
}