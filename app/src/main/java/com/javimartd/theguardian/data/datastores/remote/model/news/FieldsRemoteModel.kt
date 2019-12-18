package com.javimartd.theguardian.data.datastores.remote.model.news

import com.google.gson.annotations.SerializedName

data class FieldsRemoteModel (
        @SerializedName("liveBloggingNow")
        val liveBloggingNow: String = "",

        @SerializedName("thumbnail")
        val thumbnail: String = "",

        @SerializedName("bodyText")
        val bodyText: String = "")