package br.com.flying.dutchman.offers_challenge.data.model

import com.google.gson.annotations.SerializedName

data class Sun(
    @SerializedName("end")
    val end: String,
    @SerializedName("end_minutes")
    val endMinutes: Int,
    @SerializedName("start")
    val start: String,
    @SerializedName("start_minutes")
    val startMinutes: Int
)