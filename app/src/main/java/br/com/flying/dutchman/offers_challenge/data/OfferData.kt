package br.com.flying.dutchman.offers_challenge.data

import br.com.flying.dutchman.offers_challenge.data.model.Image
import br.com.flying.dutchman.offers_challenge.data.model.Partner

data class OfferData(
    val images: List<Image>,

    val partner: Partner,
    val title: String,
    val description: String,
    val soldOut: Boolean,
    val salePrice: Double,
    val fullPrice: Double,
    val soldUnits: Int,
    val details: String
)