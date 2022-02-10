package com.javimartd.theguardian

import android.app.Application
import com.javimartd.theguardian.ui.di.DaggerTestAppComponent
import com.javimartd.theguardian.ui.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestTheGuardianApplication: Application(), HasAndroidInjector {

    companion object {
        lateinit var instance: TestTheGuardianApplication
            private set
    }

    val component: TestAppComponent by lazy {
        DaggerTestAppComponent.create()
    }

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        instance = this
        component.injectApplication(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}