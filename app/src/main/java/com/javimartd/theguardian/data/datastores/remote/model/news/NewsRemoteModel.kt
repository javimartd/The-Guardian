package com.javimartd.theguardian.data.datastores.remote.model.news

import com.google.gson.annotations.SerializedName

data class NewsRemoteModel (
        @SerializedName("id")
        val id: String = "",

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
        val fields: FieldsRemoteModel = FieldsRemoteModel())