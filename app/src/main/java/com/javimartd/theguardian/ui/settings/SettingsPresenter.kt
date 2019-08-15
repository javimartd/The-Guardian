package com.javimartd.theguardian.ui.settings

import com.javimartd.theguardian.domain.repositories.ResourceRepository
import com.javimartd.theguardian.ui.common.BasePresenter
import javax.inject.Inject

class SettingsPresenter @Inject constructor(private val resourceRepository: ResourceRepository):
        BasePresenter<SettingsContract.View>(), SettingsContract.Presenter {

    override fun onViewAttached() {
        // does not require implementation
    }

    override fun initializeDayNightStatus(dayNightStatus: Boolean) {
        if (dayNightStatus) {
            view?.setOptionNameDayNight(resourceRepository.getNightModeOn())
        } else {
            view?.setOptionNameDayNight(resourceRepository.getNightModeOff())
        }
    }
}