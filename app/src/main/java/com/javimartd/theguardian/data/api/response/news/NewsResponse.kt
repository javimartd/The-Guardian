package com.javimartd.theguardian.data.api.response.news

import com.google.gson.annotations.SerializedName

class NewsResponse {

    @SerializedName("response")
    val newsResponse: NewsData = NewsData()
}