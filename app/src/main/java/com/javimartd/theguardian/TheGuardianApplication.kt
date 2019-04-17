package com.javimartd.theguardian

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.javimartd.theguardian.di.component.AppComponent
import com.javimartd.theguardian.di.component.DaggerAppComponent
import com.javimartd.theguardian.ui.common.ApplicationObserver
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TheGuardianApplication: Application(), HasActivityInjector {

    companion object {
        lateinit var instance: TheGuardianApplication
            private set
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setUpDagger()
        setUpApplicationLifeCycle()
    }

    private fun setUpDagger() {
        component.inject(this)
    }

    private fun setUpApplicationLifeCycle() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}