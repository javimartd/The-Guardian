package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.entities.News

interface NewsRepository {
    fun getNews(): List<News>
}