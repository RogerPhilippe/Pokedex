package br.com.fiap.phs.pokedex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("number") var number: String,
    @SerializedName("name") var name: String,
    @SerializedName("imageURL") var imageURL: String,
    @SerializedName("attack") var attack: Int,
    @SerializedName("defense") var defense: Int,
    @SerializedName("velocity") var velocity: Int,
    @SerializedName("ps") var ps: Int
) : Parcelable

var emptyPokemon = Pokemon("", "", "", 0,
    0, 0, 0)
