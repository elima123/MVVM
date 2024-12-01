package com.android.covidapiapp.domain

import com.android.covidapiapp.data.CovidRepository
import com.android.covidapiapp.data.network.model.CovidObject

class CovidListRequirement {
    private val repository = CovidRepository()
    suspend operator fun invoke(
        date:String //TEST WITH DATE DATA TYPE NEXT
    ): List<CovidObject>? = repository.getComments(date)
}
