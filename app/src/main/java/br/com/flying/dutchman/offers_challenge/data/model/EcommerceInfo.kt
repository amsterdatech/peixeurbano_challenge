package br.com.flying.dutchman.offers_challenge.data.model

import com.google.gson.annotations.SerializedName

data class EcommerceInfo(
    @SerializedName("origin_zip_code")
    val originZipCode: String
)