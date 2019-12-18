package com.javimartd.theguardian.ui.di

import android.app.Application
import com.javimartd.theguardian.TestTheGuardianApplication
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.ui.di.module.ActivityModule
import com.javimartd.theguardian.ui.di.modules.TestNetworkModule
import com.javimartd.theguardian.ui.di.modules.TestRepositoryModule
import com.javimartd.theguardian.ui.di.modules.TestUrlModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityModule::class,
    TestRepositoryModule::class,
    TestNetworkModule::class,
    TestUrlModule::class])
interface TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestAppComponent.Builder
        fun build(): TestAppComponent
    }

    fun inject(application: TestTheGuardianApplication)

    fun getNewsRepository(): NewsRepository
}