package com.javimartd.theguardian.domain.repositories

import com.javimartd.theguardian.data.response.news.Response

interface NewsRepository {
    fun getNews(): Response
}