package com.javimartd.theguardian.test

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.javimartd.theguardian.TestTheGuardianApplication
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class TestRunner: AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestTheGuardianApplication::class.java.name, context)
    }
}