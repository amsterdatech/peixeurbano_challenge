package br.com.flying.dutchman.offers_challenge.ui.offers_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.ui.Offer
import br.com.flying.dutchman.offers_challenge.ui.ViewState
import br.com.flying.dutchman.offers_challenge.ui.commons.MarginItemDecoration
import br.com.flying.dutchman.offers_challenge.ui.detail.OfferDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val viewModel: OfferListViewModel by viewModel()

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

        viewModel.viewState().observe(this, Observer {

            when (it.status) {
                ViewState.Status.SUCCESS -> {
                    adapter.items += it.data as List<Offer>
                    adapter.notifyDataSetChanged()
                }

                else -> {

                }
            }

        })
        lifecycle.addObserver(viewModel)
        setupRecyclerView()
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
                adapter = this@MainActivity.adapter
            }
    }
}

