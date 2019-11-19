package br.com.flying.dutchman.offers_challenge.model

import com.google.gson.annotations.SerializedName

data class BuyingOption(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("available_units")
    val availableUnits: Int,
    @SerializedName("booking_enabled")
    val bookingEnabled: Boolean,
    @SerializedName("buying_option_id")
    val buyingOptionId: String,
    @SerializedName("contracting_party_fee")
    val contractingPartyFee: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("disclosure")
    val disclosure: String,
    @SerializedName("ecommerce_info")
    val ecommerceInfo: EcommerceInfo,
    @SerializedName("end")
    val end: String,
    @SerializedName("fees")
    val fees: List<Any>,
    @SerializedName("full_price")
    val fullPrice: Double,
    @SerializedName("operating_data_html")
    val operatingDataHtml: String,
    @SerializedName("option_type")
    val optionType: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("percentage_saved")
    val percentageSaved: Int,
    @SerializedName("price_version")
    val priceVersion: Int,
    @SerializedName("price_version_coupons_qty")
    val priceVersionCouponsQty: Int,
    @SerializedName("sale_price")
    val salePrice: Double,
    @SerializedName("shipping_fees")
    val shippingFees: List<Any>,
    @SerializedName("sold_out")
    val soldOut: Boolean,
    @SerializedName("sold_units")
    val soldUnits: Int,
    @SerializedName("start")
    val start: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_first_level")
    val titleFirstLevel: String,
    @SerializedName("title_fourth_level")
    val titleFourthLevel: String,
    @SerializedName("title_second_level")
    val titleSecondLevel: String,
    @SerializedName("title_third_level")
    val titleThirdLevel: String,
    @SerializedName("weekly_hours")
    val weeklyHours: WeeklyHours
)