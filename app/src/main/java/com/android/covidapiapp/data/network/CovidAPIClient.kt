package com.android.covidapiapp.data.network

import com.android.covidapiapp.data.network.model.CovidObject

class CovidAPIClient {
    private val api: CovidAPIService = NetworkModuleDI() // Single Initiation to prevent many instances

    suspend fun getCovidList(date: String): List<CovidObject>? {
        return try {
            api.getComments(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

