package com.javimartd.theguardian.ui.news.factory

import com.javimartd.theguardian.ui.news.sections.*

class SectionFactory {

    companion object {
        private const val BUSINESS = "business"
        private const val MEDIA = "media"
        private const val OPINION = "opinion"
        private const val FILM = "film"
        private const val SPORT = "sport"
        private const val POLITICS = "politics"
        private const val WORLD = "world"
        private const val LIFE_AND_STYLE = "lifeandstyle"
        private const val MONEY = "money"
        private const val ARTICLE = "article"
        private const val TRAVEL = "travel"

        fun getSection(sectionId: String, sectionName: String) : Section {
            return when (sectionId) {
                BUSINESS -> Business(sectionName)
                MEDIA -> Media(sectionName)
                OPINION -> Opinion(sectionName)
                FILM -> Film(sectionName)
                SPORT -> Sport(sectionName)
                POLITICS -> Politics(sectionName)
                WORLD -> World(sectionName)
                LIFE_AND_STYLE -> LifeAndStyle(sectionName)
                MONEY -> Money(sectionName)
                ARTICLE -> Article(sectionName)
                TRAVEL -> Travel(sectionName)
                else -> Other(sectionName)
            }
        }
    }
}