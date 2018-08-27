package com.javimartd.theguardian.presentation.news

class NewsPresenter {

    var view : NewsView? = null

    fun attachView(view: NewsView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}