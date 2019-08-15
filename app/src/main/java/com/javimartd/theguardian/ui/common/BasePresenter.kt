package com.javimartd.theguardian.ui.common

import com.javimartd.theguardian.domain.common.UseCase
import com.javimartd.theguardian.domain.errors.ApiError

abstract class BasePresenter<T: BaseContract.View> : BaseContract.Presenter<T> {

    abstract fun onViewAttached()

    protected var view: T? = null

    override fun attachView(view: T?) {
        this.view = view
        onViewAttached()
    }

    override fun detachView() {
        this.view = null
    }

    fun execute(useCase: UseCase,
                onSuccess: (Any) -> Unit,
                noConnection: () -> Unit,
                apiError: (ApiError) -> Unit,
                genericError: () -> Unit) {
        useCase.execute(onSuccess, noConnection, apiError, genericError)
    }
}