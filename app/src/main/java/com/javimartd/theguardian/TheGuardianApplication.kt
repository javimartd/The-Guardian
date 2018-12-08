package com.javimartd.theguardian

import android.app.Application
import android.arch.lifecycle.ProcessLifecycleOwner
import com.javimartd.theguardian.presentation.common.ApplicationObserver

class TheGuardianApplication: Application() {

    companion object {
        lateinit var instance: TheGuardianApplication
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setUpApplicationLifeCycle()
    }

    private fun setUpApplicationLifeCycle() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}