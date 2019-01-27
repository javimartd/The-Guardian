package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.model.Location

interface LocationRepository {
    fun getDeviceLocation(): Location
}