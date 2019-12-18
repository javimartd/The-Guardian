package com.javimartd.theguardian.domain.usecases

import com.javimartd.theguardian.domain.common.ObservableUseCase
import com.javimartd.theguardian.domain.executor.PostExecutionThread
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository,
                                         postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<News>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<News>> {
        return newsRepository.getNews()
    }
}