package br.com.flying.dutchman.offers_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import br.com.flying.dutchman.offers_challenge.model.ApiResponse
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val adapter by lazy {
        OfferAdapter()
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
                            Offer(
                                it.images[0].image,
                                it.partner.name,
                                it.shortTitle,
                                it.salePrice.formatForBrazilianCurrency()
                            )
                        })

                    }

            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                activity_offers_loading.visibility = View.VISIBLE
            }
            .doOnComplete {
                activity_offers_loading.visibility = View.GONE
            }
            .subscribe(
                { offers ->
                    adapter.items = offers
                    adapter.notifyDataSetChanged()

                }, {

                }
            )
            .apply {
                compositeDisposable.add(this)
            }
    }

    private fun setupRecyclerView() {
        activity_offers_recycler_view
            .apply {
                layoutManager = GridLayoutManager(context, 1)
                addItemDecoration(
                    MarginItemDecoration(
                        resources.getDimension(R.dimen.default_padding).toInt()
                    )
                )
                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
            }
    }
}

