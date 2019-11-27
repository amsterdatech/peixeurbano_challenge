package br.com.flying.dutchman.offers_challenge.ui.offers_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flying.dutchman.domain.OfferMapper
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.data.OfferRepository
import br.com.flying.dutchman.offers_challenge.ui.commons.MarginItemDecoration
import br.com.flying.dutchman.offers_challenge.ui.detail.OfferDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    val mapper by lazy {
        OfferMapper()
    }


    val repository: OfferRepository by inject()

    private val adapter by lazy {
        OfferAdapter {
            startActivity(
                OfferDetailActivity.createIntent(
                    this,
                    it
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        loadOffersFromAssets()
    }

    private fun loadOffersFromAssets() {
        repository
            .getOffer()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                activity_offers_loading.visibility = View.VISIBLE
            }
            .doOnSuccess {
                activity_offers_loading.visibility = View.GONE
            }
            .doOnError {
                activity_offers_loading.visibility = View.GONE
            }
            .subscribe(
                { offers ->
                    /* TODO in order to simulate many items from api
                       TODO It should use swipe to refresh or some expire contraint base on time
                     */
                    adapter.items += offers.map {
                        mapper.mapFromEntity(it)
                    }
                    adapter.notifyDataSetChanged()
                },
                {

                }
            )
            .apply {
                compositeDisposable.add(this)
            }
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

