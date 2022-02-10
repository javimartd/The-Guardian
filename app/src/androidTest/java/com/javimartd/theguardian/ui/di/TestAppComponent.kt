package com.javimartd.theguardian.ui.di

import com.javimartd.theguardian.TestTheGuardianApplication
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.ui.di.module.ActivityModule
import com.javimartd.theguardian.ui.di.module.NewsViewModelModule
import com.javimartd.theguardian.ui.di.modules.TestNetworkModule
import com.javimartd.theguardian.ui.di.modules.TestRepositoryModule
import com.javimartd.theguardian.ui.di.modules.TestUrlModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityModule::class,
    TestRepositoryModule::class,
    TestNetworkModule::class,
    NewsViewModelModule::class,
    TestUrlModule::class])
interface TestAppComponent {
    fun injectApplication(application: TestTheGuardianApplication)
    fun getNewsRepository(): NewsRepository
}