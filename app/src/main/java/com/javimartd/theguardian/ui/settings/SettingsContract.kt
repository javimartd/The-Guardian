package com.javimartd.theguardian.ui.settings

import com.javimartd.theguardian.ui.common.BasePresenter
import com.javimartd.theguardian.ui.common.BaseView

interface SettingsContract {

    interface View: BaseView<Presenter> {
        fun setOptionNameDayNight(name: String)
    }

    interface Presenter: BasePresenter {
        fun initializeDayNightStatus(dayNightStatus: Boolean)
    }
}