package com.javimartd.theguardian.data.api.response.news

import com.google.gson.annotations.SerializedName

class ExtrasData {

    @SerializedName("liveBloggingNow")
    val liveBloggingNow: String = ""

    @SerializedName("thumbnail")
    val thumbnail: String = ""

    @SerializedName("bodyText")
    val bodyText: String = ""
}