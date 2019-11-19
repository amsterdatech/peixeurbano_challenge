package br.com.flying.dutchman.offers_challenge.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("company_id")
    val companyId: Int,
    @SerializedName("reviews")
    val reviews: List<Any>,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("show_score")
    val showScore: Boolean
)