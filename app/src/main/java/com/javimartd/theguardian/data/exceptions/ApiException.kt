package com.javimartd.theguardian.data.exceptions

data class ApiException(var code: Int, override var message: String): Exception()