package br.com.flying.dutchman.offers_challenge.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.load
import kotlinx.android.synthetic.main.activity_offer_detail.*
import kotlinx.android.synthetic.main.content_offer_detail.*
import kotlinx.android.synthetic.main.include_toolbar_collapsing_offer_detail.*

class OfferDetailActivity : AppCompatActivity() {

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
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.extras?.getParcelable<Offer>(OFFER)?.let {
            backdrop.load(it.images[0].image)
            offer_detail_title.text = it.title
            offer_detail_descriptions.text = it.description
        }

    }
}
