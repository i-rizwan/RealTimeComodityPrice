package com.infigo.realtimecomodityapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infigo.realtimecomodityapp.R
import com.infigo.realtimecomodityapp.models.Commodity

class CommodityAdapter : RecyclerView.Adapter<CommodityAdapter.CommodityHolder>() {

    private var list: MutableList<Commodity> = mutableListOf()


    fun setData(list: MutableList<Commodity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommodityHolder {
        return CommodityHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_commodity_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommodityHolder, position: Int) {
        val commodity = list[position]

        holder.tvCommodity.text = commodity.commodity
        holder.tvPrice.text = commodity.min_price.toString()
        holder.tvState.text = commodity.district
    }


    inner class CommodityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCommodity: TextView = itemView.findViewById(R.id.tvCommodity)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var tvState: TextView = itemView.findViewById(R.id.tvState)


    }
}