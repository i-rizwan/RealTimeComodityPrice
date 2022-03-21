package com.infigo.realtimecomodityapp.networkCall

import androidx.lifecycle.MutableLiveData
import com.infigo.realtimecomodityapp.models.Commodity

interface Repository {

    suspend fun getComoditiyData(list: MutableLiveData<MutableList<Commodity>>): MutableLiveData<MutableList<Commodity>>

    suspend fun saveData(list: MutableList<Commodity>)

    suspend fun getFilteredCommodityList(list:MutableLiveData<MutableList<Commodity>>,
                                         price: ArrayList<String>, district:ArrayList<String>):MutableLiveData<MutableList<Commodity>>

    suspend fun getSortedByPrice( list: MutableLiveData<MutableList<Commodity>>):MutableLiveData<MutableList<Commodity>>
}