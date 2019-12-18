package com.javimartd.theguardian.ui.splash

import android.os.Bundle
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.navigator.Navigator
import javax.inject.Inject

class SplashActivity: BaseActivity(), SplashContract.View {

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.navigateToNews(this)
        finish()
    }
}
