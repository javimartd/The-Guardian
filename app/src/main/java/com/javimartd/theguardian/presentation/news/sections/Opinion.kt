package com.javimartd.theguardian.presentation.news.sections

import com.javimartd.theguardian.R

class Opinion(private val sectionName: String) : Section {

    override fun getTitle(): String {
        return sectionName
    }

    override fun getColor(): Int {
        return R.color.cardview_shadow_end_color
    }
}