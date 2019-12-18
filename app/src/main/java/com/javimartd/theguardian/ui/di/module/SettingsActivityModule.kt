package com.javimartd.theguardian.ui.di.module

import com.javimartd.theguardian.domain.repositories.ResourceRepository
import com.javimartd.theguardian.ui.settings.SettingsActivity
import com.javimartd.theguardian.ui.settings.SettingsContract
import com.javimartd.theguardian.ui.settings.SettingsPresenter
import dagger.Module
import dagger.Provides

@Module
class SettingsActivityModule {

    @Provides
    fun providesSettingsPresenter(view: SettingsContract.View,
                                           resourceRepository: ResourceRepository): SettingsContract.Presenter {
        return SettingsPresenter(view, resourceRepository)
    }

    @Provides
    fun provideSettingsView(settingsActivity: SettingsActivity): SettingsContract.View {
        return settingsActivity
    }
}