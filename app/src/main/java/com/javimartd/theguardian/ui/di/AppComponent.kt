package com.javimartd.theguardian.ui.di

import android.app.Application
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.ui.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    UrlModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: TheGuardianApplication)
}