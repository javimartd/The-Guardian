package com.javimartd.theguardian.data.datasources.api.exceptions

data class ApiException(var code: Int, override var message: String): Exception()