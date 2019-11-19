package br.com.flying.dutchman.offers_challenge.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("image")
    val image: String,
    @SerializedName("original")
    val original: String,
    @SerializedName("thumb")
    val thumb: String
)