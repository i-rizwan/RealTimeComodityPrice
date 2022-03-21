package com.infigo.realtimecomodityapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.infigo.realtimecomodityapp.R
import com.infigo.realtimecomodityapp.utils.LocalPreference


class FilterValueAdapter(val selectedPosition: Int) :
    RecyclerView.Adapter<FilterValueAdapter.FilterValueHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterValueHolder {
        return FilterValueHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_filter_value_item, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return LocalPreference.filterMap[selectedPosition]?.list?.size!!
    }

    override fun onBindViewHolder(holder: FilterValueHolder, position: Int) {
        val filterValue = LocalPreference.filterMap[selectedPosition]

        var selectedList = filterValue?.selectedList

        holder.cbValue.setOnClickListener {
            if (holder.cbValue.isChecked) {
                filterValue?.list?.get(position)?.let { it1 -> selectedList?.add(it1) }
            } else {
                selectedList?.remove(filterValue?.list?.get(position))
            }

            if (selectedList != null) {
                filterValue?.selectedList = selectedList
            }

            if (filterValue != null) {
                LocalPreference.filterMap.put(selectedPosition, filterValue)
            }

        }
        holder.tvParameter.text = filterValue?.list?.get(position)

        if (filterValue?.selectedList?.contains(filterValue?.list?.get(position))!!) {
            holder.cbValue.setChecked(true)
        }

    }

    inner class FilterValueHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvParameter: TextView = itemView.findViewById(R.id.tvParameter)
        var cbValue: MaterialCheckBox = itemView.findViewById(R.id.cbValue)
    }
}