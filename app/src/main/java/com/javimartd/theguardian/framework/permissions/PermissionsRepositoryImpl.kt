package com.javimartd.theguardian.framework.permissions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.javimartd.theguardian.domain.repositories.PermissionsRepository
import javax.inject.Inject

class PermissionsRepositoryImpl @Inject constructor(val context: Context): PermissionsRepository {

    override fun hasCameraPermission() = hasPermission(android.Manifest.permission.CAMERA)

    override fun hasLocationPermission() = hasPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)

    private fun hasPermission(permission: String): Boolean =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
}