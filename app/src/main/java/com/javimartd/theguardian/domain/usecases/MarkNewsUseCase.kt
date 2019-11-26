package com.javimartd.theguardian.domain.usecases

import com.javimartd.theguardian.domain.common.CompletableUseCase
import com.javimartd.theguardian.domain.executor.PostExecutionThread
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import io.reactivex.Completable
import javax.inject.Inject

class MarkNewsUseCase @Inject constructor(private val newsRepository: NewsRepository,
                                          postExecutionThread: PostExecutionThread)
    : CompletableUseCase<MarkNewsUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return newsRepository.saveNews(params.news)
    }

    data class Params constructor(val news: News) {
        companion object {
            fun forNews(news: News): Params {
                return Params(news)
            }
        }
    }
}