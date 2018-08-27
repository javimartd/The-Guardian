package com.javimartd.theguardian.presentation

import android.app.Application

class TheGuardianApplication: Application() {

    companion object {
        lateinit var instance: TheGuardianApplication
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}