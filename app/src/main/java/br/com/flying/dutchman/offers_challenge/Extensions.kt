package br.com.flying.dutchman.offers_challenge

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.*
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

fun Double.formatDecimal():String{
    return String.format("%.0f", this)
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


fun spannable(func: () -> SpannableString) = func()
private fun span(s: CharSequence, o: Any) =
    (if (s is String) SpannableString(s) else s as? SpannableString
        ?: SpannableString("")).apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))
operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))

fun bold(s: CharSequence) = span(s, StyleSpan(android.graphics.Typeface.BOLD))
fun italic(s: CharSequence) = span(s, StyleSpan(android.graphics.Typeface.ITALIC))
fun underline(s: CharSequence) = span(s, UnderlineSpan())
fun strike(s: CharSequence) = span(s, StrikethroughSpan())
fun sup(s: CharSequence) = span(s, SuperscriptSpan())
fun sub(s: CharSequence) = span(s, SubscriptSpan())
fun size(size: Float, s: CharSequence) = span(s, RelativeSizeSpan(size))
fun color(color: Int, s: CharSequence) = span(s, ForegroundColorSpan(color))
fun background(color: Int, s: CharSequence) = span(s, BackgroundColorSpan(color))
fun url(url: String, s: CharSequence) = span(s, URLSpan(url))

