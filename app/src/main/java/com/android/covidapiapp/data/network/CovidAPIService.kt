package com.android.covidapiapp.data.network

import com.android.covidapiapp.data.network.model.CovidObject
import com.android.covidapiapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// Query and ApiKey Manager
interface CovidAPIService {
    @Headers("X-Api-Key: $API_KEY")
    // https://api.api-ninjas.com/v1/covid19?date=2023-01-01

    @GET("covid19")
    suspend fun getRawJSON(
        @Query("date") date:String // example: 2023-01-01
    ): List<CovidObject> // Response should match data type

}