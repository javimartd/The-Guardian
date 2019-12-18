package com.javimartd.theguardian.ui.settings

import com.javimartd.theguardian.domain.repositories.ResourceRepository
import javax.inject.Inject

class SettingsPresenter @Inject constructor(private val view: SettingsContract.View,
                                            private val resourceRepository: ResourceRepository):
        SettingsContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        // does not require implementation at this moment
    }

    override fun stop() {
        // does not require implementation at this moment
    }


    override fun initializeDayNightStatus(dayNightStatus: Boolean) {
        if (dayNightStatus) {
            view.setOptionNameDayNight(resourceRepository.getNightModeOn())
        } else {
            view.setOptionNameDayNight(resourceRepository.getNightModeOff())
        }
    }
}