package com.infigo.realtimecomodityapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.infigo.realtimecomodityapp.adapter.CommodityAdapter
import com.infigo.realtimecomodityapp.databinding.ActivityMainBinding
import com.infigo.realtimecomodityapp.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityMainBinding
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var mainViewModel: MainViewModel

    lateinit var adapter: CommodityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = MainViewModel(applicationContext)

        adapter = CommodityAdapter()
        binding.rvCommodity.adapter = adapter

        if (intent.getBooleanExtra("filter", false)) {
            launch {
                mainViewModel.getFilterData()
            }
        } else {
            launch {
                Log.d("TAG", "onCreate: launch")
                mainViewModel.getData()
            }
        }

        binding.tvFilter.setOnClickListener {
            startActivity(Intent(this, FilterActivity::class.java))
        }

        binding.tvSort.setOnClickListener {
            launch {
                mainViewModel.getPriceSort()
            }
        }



        mainViewModel.getLiveData().observe(this, Observer {
            adapter.setData(it)
        })

    }
}