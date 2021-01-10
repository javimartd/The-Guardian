package com.javimartd.theguardian.ui.common.state

class SimpleResource<out T> constructor(val status: Status,
                                        val data: T?,
                                        val message: String?)

enum class Status {
    LOADING, SUCCESS, ERROR
}