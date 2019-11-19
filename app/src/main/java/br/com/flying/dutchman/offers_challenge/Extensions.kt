package br.com.flying.dutchman.offers_challenge

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.DecimalFormat
import java.util.*


fun Double.formatForBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(this)
}

fun AppCompatImageView.load(url: String?, circle: Boolean = false) {
    val options = RequestOptions()
        .priority(Priority.NORMAL)
        .diskCacheStrategy(DiskCacheStrategy.DATA)

    if (circle) {
        options.circleCrop()
    }

    Glide
        .with(App.instance)
        .load(url)
        .apply(options)
        .into(this@load)
}

