package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white") val blackWhite: com.example.kotlin.mypokedexapp.data.network.model.pokemon.BlackWhite
)