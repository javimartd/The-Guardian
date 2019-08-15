package com.javimartd.theguardian.ui.settings

import com.javimartd.theguardian.ui.common.BaseContract

interface SettingsContract {

    interface View: BaseContract.View {
        fun setOptionNameDayNight(name: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun initializeDayNightStatus(dayNightStatus: Boolean)
    }
}