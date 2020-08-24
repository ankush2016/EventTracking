package com.tools.eventtrackinglib.api

import com.tools.eventtrackinglib.model.AppManagerResponse
import com.tools.eventtrackinglib.model.EventsBody
import com.tools.eventtrackinglib.model.StatusResponse
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    @POST("addEvent.php")
    fun logEvent(@Body eventsBody: EventsBody): Call<StatusResponse>

    @GET("getAppManagerValues.php")
    fun getAppManagerValues(): Call<AppManagerResponse>
}