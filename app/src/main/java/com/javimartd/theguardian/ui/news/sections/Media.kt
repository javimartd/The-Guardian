package com.javimartd.theguardian.ui.news.sections

import com.javimartd.theguardian.R

class Media(private val sectionName: String) : Section {

    override fun getTitle(): String = sectionName

    override fun getColor(): Int = R.color.media

}