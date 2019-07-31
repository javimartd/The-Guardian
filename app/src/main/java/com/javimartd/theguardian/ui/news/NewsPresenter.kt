package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.domain.errors.ApiError
import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.domain.usecases.GetNews
import com.javimartd.theguardian.ui.common.Presenter
import com.javimartd.theguardian.ui.extensions.toPresentation
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val getNews: GetNews): Presenter<NewsView>() {

    override fun onViewAttached() {
        getNews()
    }

    //region GET NEWS
    private fun getNews() {
        view.showLoading()
        execute(useCase = getNews,
                onSuccess = { showNews(it) },
                noConnection = { connectionError() },
                apiError = { apiError(it) },
                genericError = { onError() })
    }

    private fun showNews(news: Any?) {
        news as List<News>
        view.hideLoading()
        view.showNews(news.toPresentation())
    }

    private fun connectionError() {
        view.hideLoading()
        view.showConnectionError()
    }

    private fun apiError(apiError: ApiError) {
        view.hideLoading()
        view.showError(apiError.errorCode, apiError.message)
    }

    private fun onError() {
        view.hideLoading()
        view.showGenericError()
    }
    //endregion
}