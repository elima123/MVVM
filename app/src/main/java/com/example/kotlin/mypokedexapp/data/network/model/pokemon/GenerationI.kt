package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") val redBlue: com.example.kotlin.mypokedexapp.data.network.model.pokemon.RedBlue,
    val yellow: com.example.kotlin.mypokedexapp.data.network.model.pokemon.Yellow
)