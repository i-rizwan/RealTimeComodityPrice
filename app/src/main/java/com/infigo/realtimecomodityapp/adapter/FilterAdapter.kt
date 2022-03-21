package com.infigo.realtimecomodityapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infigo.realtimecomodityapp.models.FilterValue
import com.infigo.realtimecomodityapp.R

class FilterAdapter(
    val filterParameterList: HashMap<Int, FilterValue>,
    val rvFilterParameterValue: RecyclerView
) : RecyclerView.Adapter<FilterAdapter.FilterHolder>() {

    var selectedPosition: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        return FilterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_filter_parameter_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return filterParameterList.size
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        val parameterName = filterParameterList.values.toList()[position]
        holder.tvParameter.text = parameterName.name

        holder.tvParameter.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
        rvFilterParameterValue.adapter = FilterValueAdapter(selectedPosition)
        holder.tvParameter.setBackgroundColor(if (selectedPosition == position) Color.WHITE else Color.TRANSPARENT)
    }


    inner class FilterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvParameter: TextView = itemView.findViewById(R.id.tvParameter)
    }


}






