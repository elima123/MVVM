package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: com.example.kotlin.mypokedexapp.data.network.model.pokemon.UltraSunUltraMoon
)