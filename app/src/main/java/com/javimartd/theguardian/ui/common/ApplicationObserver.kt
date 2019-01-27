package com.javimartd.theguardian.ui.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

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

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPause() {
        Log.i(ApplicationObserver::class.java.simpleName, "onPause")
    }
}