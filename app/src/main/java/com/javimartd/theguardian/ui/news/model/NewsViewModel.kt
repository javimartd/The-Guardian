package com.javimartd.theguardian.ui.news.model

import com.javimartd.theguardian.ui.news.sections.Section

data class NewsViewModel(val section: Section,
                         val title: String,
                         val date: String,
                         val webUrl: String,
                         val live: Boolean,
                         val thumbnail: String,
                         val description: String) {

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is NewsViewModel) {
            return false
        }
        return section.getTitle() == other.section.getTitle()
                && title == other.title
                && date == other.date
                && webUrl == other.webUrl
    }
}