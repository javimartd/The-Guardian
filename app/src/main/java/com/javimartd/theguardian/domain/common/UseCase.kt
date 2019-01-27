package com.javimartd.theguardian.domain.common

import kotlinx.coroutines.*
import java.net.UnknownHostException

abstract class UseCase {

    abstract suspend fun execution(): Any

    private lateinit var result: Deferred<*>

    fun execute(
            onSuccess: (it: Any) -> Unit,
            noConnection: () -> Unit,
            genericError: () -> Unit
    ) {
        result = GlobalScope.async(Dispatchers.IO) {
            try {
                execution()
            }catch (exception: UnknownHostException) {
                UnknownHostException()
            } catch (exception: Exception) {
                Exception()
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            result.await()?.let {
                when (it) {
                    is UnknownHostException -> noConnection()
                    is Exception -> genericError()
                    else -> onSuccess(it)
                }
            }
        }
    }
}