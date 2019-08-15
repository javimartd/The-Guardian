package com.javimartd.theguardian.ui.webView

import com.javimartd.theguardian.ui.common.BaseContract

interface WebViewContract {

    interface View: BaseContract.View

    interface Presenter: BaseContract.Presenter<View>
}