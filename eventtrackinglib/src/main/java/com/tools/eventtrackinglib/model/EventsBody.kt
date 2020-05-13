package com.tools.eventtrackinglib.model

import com.google.gson.annotations.SerializedName

data class EventsBody(
    @SerializedName("app_id") var appId: String,
    @SerializedName("event_name") var eventName: String,
    @SerializedName("subevent_name") var subeventName: String? = null,
    @SerializedName("notes") var notes: String? = null
)