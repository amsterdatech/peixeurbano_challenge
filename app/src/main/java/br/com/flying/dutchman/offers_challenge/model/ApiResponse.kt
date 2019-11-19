package br.com.flying.dutchman.offers_challenge.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("response")
    val response: List<Offer>
)