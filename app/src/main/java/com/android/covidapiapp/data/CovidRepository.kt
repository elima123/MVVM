package com.android.covidapiapp.data

import com.android.covidapiapp.data.network.CovidAPIClient
import com.android.covidapiapp.data.network.model.CovidObject

// Passive factorization of RawJSON with the Client
class CovidRepository {
    private val api = CovidAPIClient()
    // Use as different names as possible to track the processing correctly
    suspend fun getRawJSON(date:String): List<CovidObject>? = api.getCovidList(date)
}