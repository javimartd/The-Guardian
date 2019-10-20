package com.javimartd.theguardian.domain.common

import com.javimartd.theguardian.data.datastores.remote.exceptions.ApiException
import com.javimartd.theguardian.domain.errors.Error
import kotlinx.coroutines.*
import java.net.UnknownHostException

abstract class CoroutinesUseCase {

    abstract suspend fun execution(): Any

    private lateinit var result: Deferred<*>

    fun execute(
            onSuccess: (data: Any) -> Unit,
            noConnection: () -> Unit,
            apiError: (Error) -> Unit,
            genericError: () -> Unit
    ) {
        result = GlobalScope.async(Dispatchers.IO) {
            try {
                execution()
            } catch (exception: UnknownHostException) {
                UnknownHostException()
            } catch (exception: ApiException) {
                ApiException(exception.code, exception.message)
            } catch (exception: Exception) {
                Exception()
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            result.await()?.let {
                when (it) {
                    is UnknownHostException -> noConnection()
                    is ApiException -> apiError(Error(it.code, it.message))
                    is Exception -> genericError()
                    else -> onSuccess(it)
                }
            }
        }
    }
}