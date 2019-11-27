package br.com.flying.dutchman.offers_challenge.ui.offers_list

import androidx.lifecycle.*
import br.com.flying.dutchman.domain.OfferMapper
import br.com.flying.dutchman.offers_challenge.data.OfferRepository
import br.com.flying.dutchman.offers_challenge.ui.Offer
import br.com.flying.dutchman.offers_challenge.ui.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class OfferListViewModel(val repository: OfferRepository, val mapper: OfferMapper) :
    ViewModel(),
    LifecycleObserver {


    private val viewState: MutableLiveData<ViewState<List<Offer>>> by lazy {
        MutableLiveData<ViewState<List<Offer>>>()
    }

    fun viewState() = viewState

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadOffers() {
        if (viewState.value == null) {
            viewState.postValue(ViewState(status = ViewState.Status.LOADING))
        }

        repository
            .getOffer()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    viewState.postValue(
                        ViewState(
                            status = ViewState.Status.SUCCESS,
                            data = result.map {
                                mapper.mapFromEntity(it)
                            })
                    )
                },
                {
                    viewState.postValue(ViewState(ViewState.Status.ERROR, null))
                })
            .apply {
                compositeDisposable.add(this)
            }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}