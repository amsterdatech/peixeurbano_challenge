package br.com.flying.dutchman.offers_challenge.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.load
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.include_toolbar_collapsing_offer_detail.*
import kotlinx.android.synthetic.main.offer_detail_header_content.*
import kotlin.math.abs


class OfferDetailActivity : AppCompatActivity() {
    var isShow = false
    var scrollRange = -1

    var offer: Offer? = null

    companion object {

        private const val OFFER = "offer"

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
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)

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


        offer?.let {
            backdrop.load(it.images[0].image)
            offer_detail_title.text = it.title
            offer_detail_descriptions.text = it.description
        }

    }

}
