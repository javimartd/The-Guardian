package com.javimartd.theguardian.ui.di.module

import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.news.NewsActivity
import com.javimartd.theguardian.ui.news.NewsContract
import com.javimartd.theguardian.ui.news.NewsPresenter
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule {

    @Provides
    fun providesNewsPresenter(view: NewsContract.View,
                                       getNewsUseCase: GetNewsUseCase): NewsContract.Presenter {
        return NewsPresenter(view, getNewsUseCase)
    }

    @Provides
    fun provideNewsView(newsActivity: NewsActivity): NewsContract.View {
        return newsActivity
    }
}