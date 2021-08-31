package com.javimartd.theguardian.data.datastores.remote.model.news

import com.google.gson.annotations.SerializedName

data class NewsApiResponse (
    @SerializedName("response")
    val newsResults: NewsResultsRemoteModel?
)

data class NewsResultsRemoteModel(
    @SerializedName("status")
    val status: String,

    @SerializedName("results")
    val results: List<NewsRemoteModel>?
)

data class NewsRemoteModel (
    @SerializedName("id")
    val id: String,

    @SerializedName("sectionId")
    val sectionId: String,

    @SerializedName("sectionName")
    val sectionName: String,

    @SerializedName("webPublicationDate")
    val webPublicationDate: String,

    @SerializedName("webTitle")
    val webTitle: String,

    @SerializedName("webUrl")
    val webUrl: String,

    @SerializedName("fields")
    val fields: FieldsRemoteModel?
)

data class FieldsRemoteModel (
    @SerializedName("liveBloggingNow")
    val liveBloggingNow: String?,

    @SerializedName("thumbnail")
    val thumbnail: String?,

    @SerializedName("bodyText")
    val bodyText: String?
)

