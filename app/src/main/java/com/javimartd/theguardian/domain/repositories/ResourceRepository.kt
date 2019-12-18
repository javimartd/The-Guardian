package com.javimartd.theguardian.domain.repositories

interface ResourceRepository {
    fun getNightModeOff(): String
    fun getNightModeOn(): String
}