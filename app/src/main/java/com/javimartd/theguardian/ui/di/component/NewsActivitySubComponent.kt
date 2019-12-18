package com.javimartd.theguardian.ui.di.component

import com.javimartd.theguardian.ui.news.NewsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface NewsActivitySubComponent: AndroidInjector<NewsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NewsActivity>()

}