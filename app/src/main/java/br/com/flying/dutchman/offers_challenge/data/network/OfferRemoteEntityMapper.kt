package br.com.flying.dutchman.data.remote

import br.com.flying.dutchman.offers_challenge.data.OfferData
import br.com.flying.dutchman.offers_challenge.data.model.Offer


open class OfferRemoteEntityMapper constructor() :
    RemoteEntityMapper<List<Offer>, List<OfferData>> {
    override fun mapTo(type: List<OfferData>): List<Offer> {
        return emptyList()
    }

    override fun mapFromRemote(type: List<Offer>): List<OfferData> {
        return type.map {
            OfferData(
                it.images,
                it.partner,
                it.shortTitle,
                it.shortTitle,
                it.soldOut,
                it.salePrice,
                it.fullPrice,
                it.soldUnits,
                it.details
            )
        }
    }

}