package com.javimartd.theguardian.ui.common

interface BaseContract {

    interface View

    interface Presenter<T: BaseContract.View> {
        fun attachView(view: T?)
        fun detachView()
    }
}