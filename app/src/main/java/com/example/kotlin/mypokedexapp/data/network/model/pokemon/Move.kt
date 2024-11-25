package com.example.kotlin.mypokedexapp.data.network.model.pokemon

data class Move(
    val move: com.example.kotlin.mypokedexapp.data.network.model.pokemon.MoveX,
    val version_group_details: List<com.example.kotlin.mypokedexapp.data.network.model.pokemon.VersionGroupDetail>
)