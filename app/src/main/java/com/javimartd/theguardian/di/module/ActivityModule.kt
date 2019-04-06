package com.javimartd.theguardian.di.module

import com.javimartd.theguardian.ui.news.NewsActivity
import com.javimartd.theguardian.ui.settings.SettingsActivity
import com.javimartd.theguardian.ui.splash.SplashActivity
import com.javimartd.theguardian.ui.webView.WebViewActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindNewsActivity(): NewsActivity

    @ContributesAndroidInjector
    abstract fun bindWebViewActivity(): WebViewActivity

    @ContributesAndroidInjector
    abstract fun bindSettingsActivity(): SettingsActivity
}