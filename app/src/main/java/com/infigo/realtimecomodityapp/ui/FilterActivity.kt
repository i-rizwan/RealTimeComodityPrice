package com.infigo.realtimecomodityapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infigo.realtimecomodityapp.adapter.FilterAdapter
import com.infigo.realtimecomodityapp.databinding.ActivityFilterBinding
import com.infigo.realtimecomodityapp.models.FilterValue
import com.infigo.realtimecomodityapp.utils.LocalPreference

class FilterActivity : AppCompatActivity() {

    var list:ArrayList<String> = ArrayList()
    lateinit var adapter: FilterAdapter
    private lateinit var binding: ActivityFilterBinding
    init {
        list.add("District")
        list.add("Price")
        LocalPreference.filterMap[LocalPreference.district] = FilterValue("District",
            LocalPreference.districtList, arrayListOf())
        LocalPreference.filterMap[LocalPreference.price] = FilterValue("Price",
            LocalPreference.priceList, arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_filter)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter= FilterAdapter(LocalPreference.filterMap,binding.rvFilterValues)

        binding.rvFilterParameter.adapter=adapter

        binding. applyB.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            intent.putExtra("filter",true)
            startActivity(intent)
        }

        binding. clearB.setOnClickListener {
            LocalPreference.filterMap[LocalPreference.price]?.selectedList= arrayListOf()
            LocalPreference.filterMap[LocalPreference.district]?.selectedList= arrayListOf()
            val intent=Intent(this, MainActivity::class.java)
            intent.putExtra("filter",false)
            startActivity(intent)
        }
    }
}