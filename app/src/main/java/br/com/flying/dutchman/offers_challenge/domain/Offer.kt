package br.com.flying.dutchman.offers_challenge.domain.interactor

data class Offer(
    val title: String,
    val shortTitle: String,
    val soldOut: Boolean,
    val salePrice: Double,
    val fullPrice: Double,
    val soldUnits: Int,
    val details: String
)