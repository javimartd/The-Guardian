package com.javimartd.theguardian.domain.model

data class DailyNews(val dailyNews: List<News>) {
    operator fun get(position: Int) = dailyNews[position]
    fun size() = dailyNews.size
}