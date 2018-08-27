package com.javimartd.theguardian.data.response.news

data class ArticleResponse(val sectionId: String,
                           val sectionName: String,
                           val webPublicationDate: String,
                           val webTitle: String,
                           val webUrl: String,
                           val fields: AdditionalInfoResponse)