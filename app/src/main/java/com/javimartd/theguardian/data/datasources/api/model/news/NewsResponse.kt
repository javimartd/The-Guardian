package com.javimartd.theguardian.data.datasources.api.model.news

import com.google.gson.annotations.SerializedName

data class NewsResponse (
        @SerializedName("response")
        val newsResponse: NewsData = NewsData())