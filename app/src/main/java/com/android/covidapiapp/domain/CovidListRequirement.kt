package com.android.covidapiapp.domain

import com.android.covidapiapp.data.CovidRepository
import com.android.covidapiapp.data.network.model.CovidObject

// Execute factorization from the Query
class CovidListRequirement {
    private val repository = CovidRepository()
    suspend operator fun invoke(
        date:String
    ): List<CovidObject>? = repository.getRawJSON(date)
}
