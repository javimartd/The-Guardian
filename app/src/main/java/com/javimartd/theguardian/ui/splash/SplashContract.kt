package com.javimartd.theguardian.ui.splash

import com.javimartd.theguardian.ui.common.BaseContract

interface SplashContract {

    interface View: BaseContract.View

    interface Presenter: BaseContract.Presenter<View>
}