package com.javimartd.theguardian.domain.repositories

interface PermissionsRepository {
    fun hasLocationPermission(): Boolean
    fun hasCameraPermission(): Boolean
}