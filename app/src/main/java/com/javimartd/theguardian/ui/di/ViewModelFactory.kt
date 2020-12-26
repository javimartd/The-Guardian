package com.javimartd.theguardian.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.news.viewmodel.NewsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(getNewsUseCase) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}