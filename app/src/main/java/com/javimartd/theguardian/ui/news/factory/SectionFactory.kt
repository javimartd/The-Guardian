package com.javimartd.theguardian.ui.news.factory

import com.javimartd.theguardian.ui.news.sections.*

class SectionFactory {

    companion object {

        fun getSection(sectionId: String, sectionName: String) : Section {
            return when (sectionId) {
                SectionsTypes.BUSINESS.id -> Business(sectionName)
                SectionsTypes.MEDIA.id -> Media(sectionName)
                SectionsTypes.OPINION.id -> Opinion(sectionName)
                SectionsTypes.FILM.id -> Film(sectionName)
                SectionsTypes.SPORT.id -> Sport(sectionName)
                SectionsTypes.POLITICS.id -> Politics(sectionName)
                SectionsTypes.WORLD.id -> World(sectionName)
                SectionsTypes.LIFE_AND_STYLE.id -> LifeAndStyle(sectionName)
                SectionsTypes.MONEY.id -> Money(sectionName)
                SectionsTypes.ARTICLE.id -> Article(sectionName)
                SectionsTypes.TRAVEL.id -> Travel(sectionName)
                else -> Other(sectionName)
            }
        }
    }
}