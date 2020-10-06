package com.ronybrosh.endlessviewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EndlessViewPagerAdapter(
    private val data: List<CardInfo>
) : RecyclerView.Adapter<EndlessViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val last4Digits: TextView = view.findViewById(R.id.last4Digits)

        fun bind(cardInfo: CardInfo) {
            last4Digits.text = itemView.context.getString(R.string.card_number_format, cardInfo.last4Digits)
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.view_holder_card_item, parent, false))
    }
}