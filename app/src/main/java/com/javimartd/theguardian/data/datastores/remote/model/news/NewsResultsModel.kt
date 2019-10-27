package com.javimartd.theguardian.data.datastores.remote.model.news

import com.google.gson.annotations.SerializedName

data class NewsResultsModel(

        @SerializedName("status")
        val status: String = "ok",

        @SerializedName("results")
        val results: List<NewsRemoteModel> = emptyList())