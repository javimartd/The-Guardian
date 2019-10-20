package com.javimartd.theguardian.data.datastores.remote.model.news

import com.google.gson.annotations.SerializedName

data class NewsResponseModel (
        @SerializedName("response")
        val newsResponse: NewsRemoteModel = NewsRemoteModel())