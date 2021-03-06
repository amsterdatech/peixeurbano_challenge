package br.com.flying.dutchman.offers_challenge.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import br.com.flying.dutchman.offers_challenge.*
import br.com.flying.dutchman.offers_challenge.ui.Offer
import br.com.flying.dutchman.offers_challenge.ui.commons.formatDecimal
import br.com.flying.dutchman.offers_challenge.ui.commons.load
import br.com.flying.dutchman.offers_challenge.ui.commons.spannable
import br.com.flying.dutchman.offers_challenge.ui.commons.strike
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.include_toolbar_collapsing_offer_detail.*
import kotlinx.android.synthetic.main.offer_detail_content.*
import kotlinx.android.synthetic.main.offer_detail_header_content.*
import kotlin.math.abs


class OfferDetailActivity : AppCompatActivity() {
    var isShow = false

    var offer: Offer? = null

    companion object {

         const val OFFER = "offer"

        @JvmStatic
        fun createIntent(context: Context, offer: Offer): Intent {
            return Intent(context, OfferDetailActivity::class.java).apply {
                putExtras(bundleOf(OFFER to offer))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_detail)

        offer = intent?.extras?.getParcelable<Offer>(OFFER)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addAppBarOffsetListener()
        bindOffer(offer)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_offer_detail, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.action_favorite -> {

            }

            R.id.action_share -> {

            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun addAppBarOffsetListener() {
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (!isShow) {
                    isShow = true

                    offer?.let {
                        toolbar.title = it.title
                        toolbar_layout.title = it.title
                    }
                }
            } else {
                if (isShow) {
                    isShow = false
                    toolbar.title = ""
                    toolbar_layout.title = ""
                }
            }
        })
    }

    private fun bindOffer(offer: Offer?) {
        offer?.let {
            backdrop.load(offer.images[offer.imageSelected].image)
            offer_detail_title.text = it.title
            offer_detail_descriptions.text = it.description

            offer_detail_reviews.text =
                "${it.review.reviewsCount} reviews (${it.review.score.formatDecimal()})"

            if (it.soldUnits > 0) {
                offer_detail_sold_units.text = "${it.soldUnits} vendidos"
            } else {
                offer_detail_sold_units.visibility = View.GONE
            }

            offer_detail_full_price.text =
                spannable {
                    strike(it.fullPrice)
                }
            offer_detail_price.text = it.price

            offer_detail_details.text =
                HtmlCompat.fromHtml(it.details, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

}
