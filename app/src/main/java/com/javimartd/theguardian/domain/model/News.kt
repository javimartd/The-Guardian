package com.javimartd.theguardian.domain.model

import com.javimartd.theguardian.presentation.news.sections.Section

data class News(val section: Section,
                val title: String,
                val date: String,
                val webUrl: String,
                val live: Boolean,
                val thumbnail: String)