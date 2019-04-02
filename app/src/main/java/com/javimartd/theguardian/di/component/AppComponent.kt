package com.javimartd.theguardian.di.component

import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.di.module.AppModule
import com.javimartd.theguardian.di.module.NetworkModule
import com.javimartd.theguardian.di.module.RepositoryModule
import com.javimartd.theguardian.ui.news.NewsActivity
import com.javimartd.theguardian.ui.splash.SplashActivity
import com.javimartd.theguardian.ui.webView.WebViewActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(application: TheGuardianApplication)

    fun plus(newsActivity: NewsActivity)

    fun plus(splashActivity: SplashActivity)

    fun plus(webViewActivity: WebViewActivity)
}