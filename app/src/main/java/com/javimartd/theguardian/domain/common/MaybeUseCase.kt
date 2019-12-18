package com.javimartd.theguardian.domain.common

import com.javimartd.theguardian.domain.executor.PostExecutionThread
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

abstract class MaybeUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseSingle(params: Params? = null): Maybe<T>

    open fun execute(maybeObserver: DisposableMaybeObserver<T>, params: Params? = null) {
        val maybe = this.buildUseCaseSingle(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(maybe.subscribeWith(maybeObserver))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}