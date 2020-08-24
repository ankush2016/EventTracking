package com.tools.eventtrackinglib

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tools.eventtrackinglib.api.DestinationService
import com.tools.eventtrackinglib.api.ServiceBuilder
import com.tools.eventtrackinglib.model.AppManagerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppManager {
    fun getAppManagerValues(): LiveData<AppManagerResponse> {
        val appManagerResponse = MutableLiveData<AppManagerResponse>()
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall = destinationService.getAppManagerValues()
        requestCall.enqueue(object : Callback<AppManagerResponse> {
            override fun onResponse(call: Call<AppManagerResponse>, response: Response<AppManagerResponse>) {
                if (response.isSuccessful) {
                    appManagerResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<AppManagerResponse>, t: Throwable) {
            }

        })
        return appManagerResponse
    }
}