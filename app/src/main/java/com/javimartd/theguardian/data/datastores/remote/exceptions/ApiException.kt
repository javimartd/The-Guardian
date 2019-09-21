package com.javimartd.theguardian.data.datastores.remote.exceptions

data class ApiException(var code: Int, override var message: String): Exception()