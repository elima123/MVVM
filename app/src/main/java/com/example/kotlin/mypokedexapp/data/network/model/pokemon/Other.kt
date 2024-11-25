package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: com.example.kotlin.mypokedexapp.data.network.model.pokemon.DreamWorld,
    val home: com.example.kotlin.mypokedexapp.data.network.model.pokemon.Home,
    @SerializedName("official-artwork") val officialArtwork: com.example.kotlin.mypokedexapp.data.network.model.pokemon.OfficialArtwork,
    val showdown: com.example.kotlin.mypokedexapp.data.network.model.pokemon.Showdown
)