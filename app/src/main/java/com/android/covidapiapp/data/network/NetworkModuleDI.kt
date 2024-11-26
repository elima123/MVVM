package com.android.covidapiapp.data.network

import com.android.covidapiapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModuleDI { // object substitute

    // Build OkHttpClient (No need for AuthInterceptor since API key is in the headers)
    val httpClient = OkHttpClient.Builder()
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myApi = retrofit.create(CovidAPIService::class.java)

    operator fun invoke(): CovidAPIService {
        return myApi
    }
}