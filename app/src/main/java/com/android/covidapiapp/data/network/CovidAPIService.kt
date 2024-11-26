package com.android.covidapiapp.data.network

import com.android.covidapiapp.data.network.model.CovidObject
import com.android.covidapiapp.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

// API SERVICE

interface CovidAPIService {
    @Headers("X-Api-Key: $API_KEY")
    @GET("covid19?date=2022-01-01")
    fun getComments(): Call<List<CovidObject>>
}