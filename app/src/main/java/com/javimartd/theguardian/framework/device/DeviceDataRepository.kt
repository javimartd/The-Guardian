package com.javimartd.theguardian.framework.device

import android.content.Context
import com.javimartd.theguardian.domain.repositories.DeviceRepository

class DeviceDataRepository(private val context: Context): DeviceRepository {

    override fun getDeviceId(): String {
        return android.provider.Settings.System.getString(context.contentResolver,
                android.provider.Settings.Secure.ANDROID_ID)
    }
}