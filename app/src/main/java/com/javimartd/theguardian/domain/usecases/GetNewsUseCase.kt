package com.javimartd.theguardian.domain.usecases

import com.javimartd.theguardian.domain.common.SingleUseCase
import com.javimartd.theguardian.domain.executor.PostExecutionThread
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository,
                                         postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<News>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseSingle(params: Nothing?): Single<List<News>> {
        return newsRepository.getNews()
    }
}