package com.javimartd.theguardian

import android.app.Activity
import android.app.Application
import com.javimartd.theguardian.ui.di.DaggerTestAppComponent
import com.javimartd.theguardian.ui.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestTheGuardianApplication: Application(), HasActivityInjector {

    companion object {
        lateinit var instance: TestTheGuardianApplication
            private set
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    val component: TestAppComponent by lazy {
        DaggerTestAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setUpDagger()
    }

    private fun setUpDagger() {
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}