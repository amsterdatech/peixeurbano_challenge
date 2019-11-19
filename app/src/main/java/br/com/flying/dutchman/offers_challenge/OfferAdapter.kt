package br.com.flying.dutchman.offers_challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_offer.view.*
import kotlin.properties.Delegates

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    var items: List<Offer> by Delegates.observable(mutableListOf()) { _, _, _ -> notifyDataSetChanged() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.view_holder_offer,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class ViewHolder(var containerView: View) :
        RecyclerView.ViewHolder(containerView) {

        fun bind(
            item: Offer
        ) {
            containerView.offer_thumb.load(item.thumb)
            containerView.offer_title.text = item.title
            containerView.offer_description.text = item.description
            containerView.offer_price.text = item.price
        }

    }
}