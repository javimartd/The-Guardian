package com.javimartd.theguardian.ui.news

sealed class DataItemViewModel {

    data class NewsViewModel(override val id: Long): DataItemViewModel() {
    }

    object Header: DataItemViewModel() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}