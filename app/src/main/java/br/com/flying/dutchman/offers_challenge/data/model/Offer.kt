package br.com.flying.dutchman.offers_challenge.data.model

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("location")
    val location: List<Location>,
    @SerializedName("locations")
    val locations: List<Location>,
    @SerializedName("partner")
    val partner: Partner,
    @SerializedName("short_title")
    val shortTitle: String,
    @SerializedName("sold_out")
    val soldOut: Boolean,
    @SerializedName("sale_price")
    val salePrice: Double,
    @SerializedName("full_price")
    val fullPrice: Double,
    @SerializedName("sold_units")
    val soldUnits: Int,
    @SerializedName("highlights")
    val details: String

)