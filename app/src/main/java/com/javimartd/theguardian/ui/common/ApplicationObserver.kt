package com.javimartd.theguardian.ui.common


import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ApplicationObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun onForeground() {
        // App goes to foreground
        Log.i(ApplicationObserver::class.java.simpleName, "onForeground")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun onBackground() {
        // App goes to background
        Log.i(ApplicationObserver::class.java.simpleName, "onBackground")
    }
}