package com.javimartd.theguardian.ui.common.state

sealed class Resource<T>(val data: T? = null,
                         val message: String? = "",
                         val error: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, e: Throwable, data: T? = null) : Resource<T>(data, message, e)
}
