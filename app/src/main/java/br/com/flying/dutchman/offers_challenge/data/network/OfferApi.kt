package br.com.flying.dutchman.offers_challenge.data.network

import br.com.flying.dutchman.offers_challenge.data.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET


interface OfferApi {


    companion object {
        const val KEY = ""
        const val BASE_URL = "https://raw.githubusercontent.com/PeixeUrbano/desafio-android/master/"
    }

    @GET("api/deals.json")
    fun fetchOffers(): Single<ApiResponse>
}