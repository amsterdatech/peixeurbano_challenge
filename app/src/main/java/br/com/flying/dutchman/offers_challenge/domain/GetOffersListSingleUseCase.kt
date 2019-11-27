package com.dutchtechnologies.domain.interactor

import br.com.flying.dutchman.domain.OfferMapper
import br.com.flying.dutchman.offers_challenge.data.OfferData
import br.com.flying.dutchman.offers_challenge.data.OfferRepository
import br.com.flying.dutchman.offers_challenge.domain.interactor.Offer
import io.reactivex.Scheduler
import io.reactivex.Single


class GetOffersListSingleUseCase constructor(
    private val repository: OfferRepository,
    private val mapper: OfferMapper,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<List<Offer>, Int>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: Int?): Single<List<Offer>> {
        val page = params?.let {
            it
        } ?: 0

        if (page > 0) {
            repository.getOffer()
        }

        return repository
            .getOffer()
            .map { list ->
//                list.map {
//                    mapper.mapFromEntity(it)
//                }
                emptyList<Offer>()
            }
    }


}