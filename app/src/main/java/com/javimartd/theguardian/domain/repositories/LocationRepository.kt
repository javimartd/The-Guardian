package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.entities.Location

interface LocationRepository {
    fun getDeviceLocation(): Location
}