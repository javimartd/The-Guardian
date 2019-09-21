package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.domain.errors.ApiError
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.common.BasePresenter
import com.javimartd.theguardian.ui.extensions.toPresentation
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val getNews: GetNewsUseCase):
        BasePresenter<NewsContract.View>(), NewsContract.Presenter {

    override fun onViewAttached() {
        getNews()
    }

    //region GET NEWS
    private fun getNews() {
        view?.showLoading()
        execute(useCase = getNews,
                onSuccess = { onSuccess(it) },
                noConnection = { onConnectionError() },
                apiError = { onApiError(it) },
                genericError = { onError() })
    }

    private fun onSuccess(news: Any?) {
        news as List<News>
        view?.hideLoading()
        if (news.isEmpty()) {
            showEmptyState()
        } else {
            showNews(news)
        }
    }

    private fun onConnectionError() {
        view?.hideLoading()
        view?.showConnectionError()
    }

    private fun onApiError(apiError: ApiError) {
        view?.hideLoading()
        view?.showError(apiError.errorCode, apiError.message)
    }

    private fun onError() {
        view?.hideLoading()
        view?.showGenericError()
    }

    private fun showNews(news: List<News>) {
        view?.showNews(news.toPresentation())
    }

    private fun showEmptyState() {
        view?.showEmptyState()
    }
    //endregion
}