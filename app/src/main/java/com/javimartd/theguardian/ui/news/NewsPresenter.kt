package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.extensions.toPresentation
import io.reactivex.observers.DisposableObserver
import java.net.UnknownHostException
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val view: NewsContract.View,
                                        private val getNewsUseCase: GetNewsUseCase)
    : NewsContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        getNews()
    }

    override fun stop() {
        getNewsUseCase.dispose()
    }

    override fun getNews() {
        view.showLoading()
        getNewsUseCase.execute(NewsSubscriber())
    }

    //region GET NEWS
    inner class NewsSubscriber: DisposableObserver<List<News>>() {

        override fun onError(e: Throwable) {
            view.hideLoading()
            if (e is UnknownHostException) {
                view.showConnectionError()
            } else {
                view.showError()
            }
        }

        override fun onComplete() {
            // does not require implementation at this moment
        }

        override fun onNext(news: List<News>) {
            view.hideLoading()
            if (news.isEmpty()) {
                view.showEmptyState()
            } else {
                view.showNews(news.toPresentation())
            }
        }
    }
    //endregion
}