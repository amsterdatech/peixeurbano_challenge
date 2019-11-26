package br.com.flying.dutchman.offers_challenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flying.dutchman.offers_challenge.App
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.formatForBrazilianCurrency
import br.com.flying.dutchman.offers_challenge.model.ApiResponse
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val adapter by lazy {
        OfferAdapter {
            startActivity(OfferDetailActivity.createIntent(this, it))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        loadOffersFromAssets()
    }

    private fun loadOffersFromAssets() {
        Observable
            .defer {
                Observable
                    .just(Gson().fromJson(App.offersJson(), ApiResponse::class.java))
                    .subscribeOn(Schedulers.io())
                    .flatMap { apiResponse ->
                        return@flatMap Observable.just(apiResponse.response.map {
                            mapper(it)
                        })

                    }

            }
            .debounce(1,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                activity_offers_loading.visibility = View.VISIBLE
            }
            .doOnComplete {
                activity_offers_loading.visibility = View.GONE
            }
            .subscribe(
                { offers ->
                    adapter.items += offers
                    adapter.notifyDataSetChanged()
                },
                {

                }
            )
            .apply {
                compositeDisposable.add(this)
            }
    }

    private fun mapper(it: br.com.flying.dutchman.offers_challenge.model.Offer): Offer {
        return Offer(
            it.images.map { image ->
                Image(
                    image.image,
                    image.original,
                    image.thumb
                )
            },
            it.partner.name,
            it.shortTitle,
            it.salePrice.formatForBrazilianCurrency(),
            it.fullPrice.formatForBrazilianCurrency(),
            it.soldUnits,
            Review(
                it.partner.review.reviewsCount,
                it.partner.review.score
            )
        )
    }

    private fun setupRecyclerView() {
        activity_offers_recycler_view
            .apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(
                    MarginItemDecoration(
                        resources.getDimension(R.dimen.activity_vertical_margin).toInt()
                    )
                )
//                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
            }
    }
}

