package com.javimartd.theguardian.data.datasources.api.model.news

import com.google.gson.annotations.SerializedName

data class ArticleData (
        @SerializedName("sectionId")
        val sectionId: String = "",

        @SerializedName("sectionName")
        val sectionName: String = "",

        @SerializedName("webPublicationDate")
        val webPublicationDate: String = "",

        @SerializedName("webTitle")
        val webTitle: String = "",

        @SerializedName("webUrl")
        val webUrl: String = "",

        @SerializedName("fields")
        val fields: ExtrasData = ExtrasData())