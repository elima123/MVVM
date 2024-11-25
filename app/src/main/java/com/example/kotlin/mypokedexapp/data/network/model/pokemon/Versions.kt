package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Versions(

    @SerializedName("generation-i") val generationI: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationI,
    @SerializedName("generation-ii") val generationIi: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationIi,
    @SerializedName("generation-iii") val generationIii: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationIii,
    @SerializedName("generation-iv") val generationIv: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationIv,
    @SerializedName("generation-v") val generationV: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationV,
    @SerializedName("generation-vi") val generationVi: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationVi,
    @SerializedName("generation-vii") val generationVii: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationVii,
    @SerializedName("generation-viii") val generationViii: com.example.kotlin.mypokedexapp.data.network.model.pokemon.GenerationViii

)
