package com.javimartd.theguardian.ui.news.model

import com.javimartd.theguardian.ui.news.sections.Section

data class NewsViewModel(val section: Section,
                         val title: String,
                         val date: String,
                         val webUrl: String,
                         val live: Boolean,
                         val thumbnail: String)