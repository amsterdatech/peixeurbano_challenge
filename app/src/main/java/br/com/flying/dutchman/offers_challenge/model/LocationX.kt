package br.com.flying.dutchman.offers_challenge.model

import com.google.gson.annotations.SerializedName

data class LocationX(
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("additional_address_info")
    val additionalAddressInfo: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("canonical_url")
    val canonicalUrl: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("city_slug")
    val citySlug: String,
    @SerializedName("cnpj")
    val cnpj: String,
    @SerializedName("company_name")
    val companyName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("distance")
    val distance: String,
    @SerializedName("distanceLong")
    val distanceLong: Int,
    @SerializedName("formatted_url")
    val formattedUrl: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("neighborhood_slug_name")
    val neighborhoodSlugName: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("place_name")
    val placeName: String,
    @SerializedName("refDistance")
    val refDistance: String,
    @SerializedName("refDistanceLong")
    val refDistanceLong: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("zip_code")
    val zipCode: String
)