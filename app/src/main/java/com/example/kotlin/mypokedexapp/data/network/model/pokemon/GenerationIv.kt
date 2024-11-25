package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl") val diamondPearl: com.example.kotlin.mypokedexapp.data.network.model.pokemon.DiamondPearl,
    @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: com.example.kotlin.mypokedexapp.data.network.model.pokemon.HeartgoldSoulsilver,
    val platinum: com.example.kotlin.mypokedexapp.data.network.model.pokemon.Platinum
)