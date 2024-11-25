package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: com.example.kotlin.mypokedexapp.data.network.model.pokemon.OmegarubyAlphasapphire,
    @SerializedName("x-y") val xy: com.example.kotlin.mypokedexapp.data.network.model.pokemon.XY
)