package com.javimartd.theguardian.di.component

import android.app.Application
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.di.module.ActivityModule
import com.javimartd.theguardian.di.module.AppModule
import com.javimartd.theguardian.di.module.NetworkModule
import com.javimartd.theguardian.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, AppModule::class, RepositoryModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: TheGuardianApplication)
}