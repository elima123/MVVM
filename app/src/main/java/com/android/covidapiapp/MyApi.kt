package com.android.covidapiapp

import com.android.covidapiapp.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

// API SERVICE

interface MyApi {
    @Headers("X-Api-Key: $API_KEY")
    @GET("covid19?date=2022-01-01")
    fun getComments(): Call<List<Comments>>

}

// covid19?date=2022-01-01

// comments