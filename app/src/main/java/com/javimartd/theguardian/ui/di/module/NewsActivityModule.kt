package com.javimartd.theguardian.ui.di.module

import com.javimartd.theguardian.domain.usecases.GetNews
import com.javimartd.theguardian.ui.news.NewsActivity
import com.javimartd.theguardian.ui.news.NewsContract
import com.javimartd.theguardian.ui.news.NewsPresenter
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule {

    @Provides
    internal fun providesNewsPresenter(newsView: NewsContract.View, getNews: GetNews): NewsContract.Presenter {
        return NewsPresenter(getNews)
    }

    @Provides
    internal fun provideNewsView(newsActivity: NewsActivity): NewsContract.View {
        return newsActivity
    }
}