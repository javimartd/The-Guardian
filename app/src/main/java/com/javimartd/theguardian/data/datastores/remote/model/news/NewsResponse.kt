package com.javimartd.theguardian.data.datastores.remote.model.news

import com.google.gson.annotations.SerializedName

data class NewsResponse (
        @SerializedName("response")
        val newsResponse: NewsData = NewsData())