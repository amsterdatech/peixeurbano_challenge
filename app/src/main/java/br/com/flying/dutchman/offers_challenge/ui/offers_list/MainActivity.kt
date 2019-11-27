package br.com.flying.dutchman.offers_challenge.ui.offers_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.ui.Offer
import br.com.flying.dutchman.offers_challenge.ui.ViewState
import br.com.flying.dutchman.offers_challenge.ui.commons.MarginItemDecoration
import br.com.flying.dutchman.offers_challenge.ui.commons.hide
import br.com.flying.dutchman.offers_challenge.ui.commons.makeSnackbarWithAction
import br.com.flying.dutchman.offers_challenge.ui.commons.show
import br.com.flying.dutchman.offers_challenge.ui.detail.OfferDetailActivity
import br.com.flying.dutchman.offers_challenge.ui.detail.OfferDetailActivity.Companion.OFFER
import com.google.android.material.snackbar.Snackbar
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

        savedInstanceState?.let {
            if (it.containsKey(OFFER)) {
                adapter.items = savedInstanceState.getParcelableArrayList(OFFER)
            }
        }


        viewModel
            .viewState()
            .observe(this,
                Observer {

                    when (it.status) {
                        ViewState.Status.SUCCESS -> {
                            showList(it)
                        }

                        ViewState.Status.ERROR -> {
                            showError()
                        }

                        ViewState.Status.LOADING -> {
                            activity_offers_loading.show()
                            activity_offers_recycler_view.hide()
                            activity_offers_loading.show()
                        }
                    }
                })

//        lifecycle.addObserver(viewModel)
        viewModel.loadOffers()
        setupRecyclerView()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        if (adapter.items.isNotEmpty()) outState.putParcelableArrayList(
            OFFER,
            adapter.items as ArrayList
        )

        super.onSaveInstanceState(outState)
    }

    private fun showList(it: ViewState<List<Offer>>) {
        activity_offers_loading.hide()
        activity_offers_recycler_view.show()
        adapter.items += it.data as List<Offer>
        adapter.notifyDataSetChanged()
    }

    private fun showError() {
        activity_offers_loading.hide()
        activity_offers_content_root
            .makeSnackbarWithAction(
                R.string.error_message,
                Snackbar.LENGTH_LONG,
                R.string.retry, View.OnClickListener {
                    viewModel.loadOffers()
                }
            )
            .show()
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

