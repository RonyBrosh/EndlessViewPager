package com.ronybrosh.endlessviewpager.adaper

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ronybrosh.endlessviewpager.R
import com.ronybrosh.endlessviewpager.model.CardInfo
import com.ronybrosh.endlessviewpager.model.CardStatus
import com.ronybrosh.endlessviewpager.util.BitmapUtil

class EndlessViewPagerAdapter(
    resources: Resources,
    private val data: List<CardInfo>
) : RecyclerView.Adapter<EndlessViewPagerAdapter.ViewHolder>() {

    private val frozenCardDrawable = BitmapUtil.createMaskedDrawable(
        resources,
        R.drawable.ic_card_item_image,
        R.mipmap.card_frozen_overlay
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val last4Digits: TextView = view.findViewById(R.id.last4Digits)
        private val cardImage: ImageView = view.findViewById(R.id.cardImage)

        fun bind(cardInfo: CardInfo) {
            last4Digits.text =
                itemView.context.getString(R.string.card_number_format, cardInfo.last4Digits)

            when (cardInfo.status) {
                CardStatus.ACTIVE -> cardImage.setImageResource(R.drawable.ic_card_item_image)
                CardStatus.FROZEN -> cardImage.setImageDrawable(frozenCardDrawable)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.view_holder_card_item, parent, false))
    }
}