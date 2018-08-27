package com.javimartd.theguardian.presentation.news.sections

class World(private val sectionName: String) : Section {

    override fun getTitle(): String {
        return sectionName
    }

    override fun getColor(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}