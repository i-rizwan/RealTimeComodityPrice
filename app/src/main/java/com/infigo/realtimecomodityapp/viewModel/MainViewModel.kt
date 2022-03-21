package com.infigo.realtimecomodityapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infigo.realtimecomodityapp.localDB.LocalDatabase
import com.infigo.realtimecomodityapp.localDB.LocalStorage
import com.infigo.realtimecomodityapp.models.Commodity
import com.infigo.realtimecomodityapp.utils.LocalPreference

class MainViewModel(context: Context) : ViewModel() {
    private var myContext = context;
    private val localDataSource = getLocalRepo()

    private var repo: CommodityRemoteRepo = CommodityRemoteRepo(localDataSource, myContext)

    var commodityList: MutableLiveData<MutableList<Commodity>> = MutableLiveData()


    init {
        if (!LocalPreference.isFirstLaunch) {
            repo.getCommodity()
            LocalPreference.isFirstLaunch = true
        }
    }

    fun getLocalRepo(): LocalStorage {
        val database = LocalDatabase.getDataBase(myContext)
        return LocalStorage(database?.commodityDao()!!)
    }

    suspend fun getData() {
        localDataSource.getComoditiyData(commodityList)
        Log.d("", "getData: ")
    }

    fun getLiveData(): LiveData<MutableList<Commodity>> {
        return this.commodityList
    }


    suspend fun getFilterData() {
        commodityList = localDataSource.getFilteredCommodityList(
            commodityList,
            LocalPreference.filterMap[LocalPreference.price]?.selectedList!!,
            LocalPreference.filterMap[LocalPreference.district]?.selectedList!!
        )
    }

    suspend fun getPriceSort() {
        commodityList = localDataSource.getSortedByPrice(commodityList)
    }


}