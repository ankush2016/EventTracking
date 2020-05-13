package com.tools.eventtrackinglib.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StatusResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
) : Serializable