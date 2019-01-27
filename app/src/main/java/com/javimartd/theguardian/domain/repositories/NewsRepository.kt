package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.domain.model.News

interface NewsRepository {
    fun getNews(): List<News>
}