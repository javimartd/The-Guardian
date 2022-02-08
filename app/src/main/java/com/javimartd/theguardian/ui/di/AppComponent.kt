package com.javimartd.theguardian.ui.di

import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.ui.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    NewsViewModelModule::class,
    UrlModule::class])
interface AppComponent {
    fun injectApplication(application: TheGuardianApplication)
}