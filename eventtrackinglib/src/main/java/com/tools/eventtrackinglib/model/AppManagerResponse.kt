package com.tools.eventtrackinglib.model

import com.google.gson.annotations.SerializedName

data class AppManagerResponse(
    @SerializedName("data") var data: ArrayList<AppManagerData>? = null
)

data class AppManagerData(
    @SerializedName("app_id") var appId: String,
    @SerializedName("app_key") var appKey: String,
    @SerializedName("value") var value: String
)