package com.javimartd.theguardian.data.response.news

data class NewsResponse(val status: String,
                        val results: List<ArticleResponse>)