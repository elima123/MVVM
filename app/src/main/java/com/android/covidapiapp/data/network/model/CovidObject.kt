package com.android.covidapiapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CovidObject(
    @SerializedName("country")val country: String,
    @SerializedName("region")val region: String,
    @SerializedName("cases")val cases: Cases
)

data class Cases(
    @SerializedName("total")val total: Int,
    @SerializedName("new")val new: Int
)



