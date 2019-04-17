package com.javimartd.theguardian.framework.resources

import android.content.res.Resources
import com.javimartd.theguardian.R
import com.javimartd.theguardian.domain.repositories.ResourceRepository
import javax.inject.Inject

class ResourceDataRepository @Inject constructor(val res: Resources): ResourceRepository {

    override fun getNightModeOff(): String = res.getString(R.string.option_night_mode_off)

    override fun getNightModeOn(): String = res.getString(R.string.option_night_mode_on)
}