package com.javimartd.theguardian

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ProcessLifecycleOwner
import com.javimartd.theguardian.ui.common.ApplicationObserver
import com.javimartd.theguardian.ui.di.DaggerAppComponent
import com.javimartd.theguardian.ui.extensions.DelegatesExt
import com.javimartd.theguardian.ui.settings.SettingsActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TheGuardianApplication: Application(), HasAndroidInjector {

    companion object {
        lateinit var instance: TheGuardianApplication
            private set
    }

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        instance = this
        checkNightMode()
        setUpDagger()
        setUpApplicationLifeCycle()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun checkNightMode() {
        val nightMode: Boolean by DelegatesExt.preference(
            this,
            SettingsActivity.OPTION1,
            SettingsActivity.OPTION1_DEFAULT
        )
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setUpDagger() {
        DaggerAppComponent.create().injectApplication(this)
    }

    private fun setUpApplicationLifeCycle() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}