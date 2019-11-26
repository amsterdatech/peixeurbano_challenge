package br.com.flying.dutchman.offers_challenge.ui.offers_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.flying.dutchman.offers_challenge.R
import br.com.flying.dutchman.offers_challenge.ui.commons.load
import br.com.flying.dutchman.offers_challenge.ui.Offer
import kotlinx.android.synthetic.main.view_holder_offer_banner.view.*
import kotlin.properties.Delegates

class OfferAdapter(private val action: (Offer) -> Unit? = {}) :
    RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    companion object {

        const val BANNER_TYPE = 1
        const val LIST_ITEM_TYPE = 0

    }

    var items: List<Offer> by Delegates.observable(mutableListOf()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            BANNER_TYPE -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_offer_banner,
                        parent,
                        false
                    ),
                    viewType
                )
            }

            LIST_ITEM_TYPE -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_offer_list_item,
                        parent,
                        false
                    ), viewType
                )
            }

            else -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_offer_list_item,
                        parent,
                        false
                    ), viewType
                )
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        if (position % 6 == 0 || position == 0) {
            return BANNER_TYPE
        }

        return LIST_ITEM_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.containerView.custom_view_offer_content_root.setOnClickListener {
            action.invoke(item)
        }

        holder.containerView.offer_thumb?.setOnClickListener {
            action.invoke(item)
        }
    }


    class ViewHolder(
        var containerView: View,
        var type: Int = LIST_ITEM_TYPE
    ) :
        RecyclerView.ViewHolder(containerView) {

        fun bind(
            item: Offer, randomImage: Int = (0 until item.images.size).random()
        ) {

            item.imageSelected = randomImage
            if (type == BANNER_TYPE) {
                containerView.offer_thumb.load(item.images[randomImage].image)
            } else {
                containerView.offer_thumb.load(item.images[randomImage].thumb)
            }

            containerView.offer_title.text = item.title
            containerView.offer_description.text = item.description
            containerView.offer_price.text = item.price
        }


    }
}