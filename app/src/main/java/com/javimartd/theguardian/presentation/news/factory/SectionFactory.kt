package com.javimartd.theguardian.presentation.news.factory

import com.javimartd.theguardian.presentation.news.sections.*


class SectionFactory {

    companion object {
        private const val BUSINESS = "business"
        private const val MEDIA = "media"
        private const val OPINION = "opinion"
        private const val FILM = "film"
        private const val SPORT = "sport"
        private const val POLITICS = "politics"
        private const val WORLD = "world"

        fun getSection(sectionId: String, sectionName: String) : Section {
            return when (sectionId) {
                BUSINESS -> Business(sectionName)
                MEDIA -> Media(sectionName)
                OPINION -> Opinion(sectionName)
                FILM -> Film(sectionName)
                SPORT -> Sport(sectionName)
                POLITICS -> Politics(sectionName)
                WORLD -> World(sectionName)
                else -> Other(sectionName)
            }
        }
    }
}