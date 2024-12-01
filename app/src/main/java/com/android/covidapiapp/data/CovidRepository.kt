package com.android.covidapiapp.data

import com.android.covidapiapp.data.network.CovidAPIClient
import com.android.covidapiapp.data.network.model.CovidObject


class CovidRepository {
    private val api = CovidAPIClient()
    suspend fun getComments(date:String): List<CovidObject>? = api.getCovidList(date)
}