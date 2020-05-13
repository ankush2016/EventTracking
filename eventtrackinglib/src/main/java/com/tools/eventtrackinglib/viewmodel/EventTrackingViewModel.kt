package com.tools.eventtrackinglib.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tools.eventtrackinglib.api.DestinationService
import com.tools.eventtrackinglib.api.ServiceBuilder
import com.tools.eventtrackinglib.model.EventsBody
import com.tools.eventtrackinglib.model.StatusResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventTrackingViewModel(application: Application) : AndroidViewModel(application) {

    fun logEvent(appId: String, eventName: String, subeventName: String?): LiveData<StatusResponse> {
        val statusResponse = MutableLiveData<StatusResponse>()
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val eventsBody = EventsBody(appId, eventName, subeventName)
        val requestCall = destinationService.logEvent(eventsBody)
        requestCall.enqueue(object :Callback<StatusResponse>{
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                if (response.isSuccessful) {
                    statusResponse.postValue(response.body())
                }
            }
        })
        return statusResponse
    }

}