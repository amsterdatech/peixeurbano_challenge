package br.com.flying.dutchman.domain

import br.com.flying.dutchman.offers_challenge.data.OfferData
import br.com.flying.dutchman.offers_challenge.ui.Image
import br.com.flying.dutchman.offers_challenge.ui.Offer
import br.com.flying.dutchman.offers_challenge.ui.Review
import br.com.flying.dutchman.offers_challenge.ui.commons.formatForBrazilianCurrency


class OfferMapper constructor(
) :
    Mapper<OfferData, Offer> {
    override fun mapFromEntity(type: OfferData): Offer {
        return Offer(
            type.images.map {
                Image(it.image, it.original, it.thumb)
            },
            0,
            type.partner.name,
            type.description,
            type.salePrice.formatForBrazilianCurrency(),
            type.fullPrice.formatForBrazilianCurrency(),
            type.soldUnits,
            Review(type.partner.review.reviewsCount, type.partner.review.score),
            type.details
        )
    }

    //  override fun mapToEntity(type: Offer): OfferData {
//

    //}

}