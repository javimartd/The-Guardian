package com.javimartd.theguardian

import android.app.Application
import android.arch.lifecycle.ProcessLifecycleOwner
import com.javimartd.theguardian.di.component.AppComponent
import com.javimartd.theguardian.di.component.DaggerAppComponent
import com.javimartd.theguardian.ui.common.ApplicationObserver

class TheGuardianApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: TheGuardianApplication
        private set
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
}