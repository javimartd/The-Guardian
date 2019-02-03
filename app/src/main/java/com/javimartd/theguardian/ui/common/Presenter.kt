package com.javimartd.theguardian.ui.common

import com.javimartd.theguardian.domain.common.UseCase
import com.javimartd.theguardian.domain.errors.ApiError

abstract class Presenter<T: RequestView> {

    abstract fun onViewAttached()

    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view
        onViewAttached()
    }

    fun execute(useCase: UseCase,
                onSuccess: (Any) -> Unit,
                noConnection: () -> Unit,
                apiError: (ApiError) -> Unit,
                genericError: () -> Unit) {
        useCase.execute(onSuccess, noConnection, apiError, genericError)
    }
}