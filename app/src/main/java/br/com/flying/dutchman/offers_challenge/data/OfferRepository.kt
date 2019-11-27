package br.com.flying.dutchman.offers_challenge.data

import br.com.flying.dutchman.data.remote.OfferRemoteEntityMapper
import br.com.flying.dutchman.offers_challenge.data.local.OfferDao
import br.com.flying.dutchman.offers_challenge.data.network.OfferApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class OfferRepository(
    private val api: OfferApi,
    private val dao: OfferDao,
    private val remoteMapper: OfferRemoteEntityMapper
) {

    fun getOffer(): Single<List<OfferData>> {
        return api
            .fetchOffers()
            .subscribeOn(Schedulers.io())
            .map { apiResponse ->
                remoteMapper
                    .mapFromRemote(apiResponse.response)
            }
    }

}