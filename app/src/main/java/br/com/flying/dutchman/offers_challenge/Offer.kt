package br.com.flying.dutchman.offers_challenge

data class Offer(
    val images:List<Image>,
    val title: String,
    val description: String,
    val price: String
)

data class Image(
    val image: String,
    val original: String,
    val thumb: String
)
