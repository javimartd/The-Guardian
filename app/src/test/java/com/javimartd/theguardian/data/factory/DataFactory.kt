package com.javimartd.theguardian.data.factory

import java.util.*

object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }
}