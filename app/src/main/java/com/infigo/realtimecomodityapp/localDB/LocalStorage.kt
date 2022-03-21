package com.infigo.realtimecomodityapp.localDB

import androidx.lifecycle.MutableLiveData
import com.infigo.realtimecomodityapp.models.Commodity
import com.infigo.realtimecomodityapp.networkCall.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalStorage(private val roomDBDao: RoomDBDao) : Repository {

    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getComoditiyData(list: MutableLiveData<MutableList<Commodity>>): MutableLiveData<MutableList<Commodity>> =
        withContext(ioDispatcher) {
            list.postValue(roomDBDao.getCommodityByPrice())
            return@withContext list

        }

    override suspend fun saveData(list: MutableList<Commodity>) {
        withContext(ioDispatcher) {
            roomDBDao.saveCommodities(list)
        }
    }

    override suspend fun getFilteredCommodityList(
        list: MutableLiveData<MutableList<Commodity>>,
        price: ArrayList<String>,
        district: ArrayList<String>
    ):
            MutableLiveData<MutableList<Commodity>> = withContext(ioDispatcher) {

         if (price.isNotEmpty()&&district.isNotEmpty()){
             list.postValue(roomDBDao.getFilter(price,district))
         }else if (price.isEmpty()){
             list.postValue(roomDBDao.getDistrictFilter(district))
         }else if (district.isEmpty()){
             list.postValue(roomDBDao.getPriceFilter(price))
         }else{
             list.postValue(roomDBDao.getCommodityByPrice())
         }

        return@withContext list
    }

    override suspend fun getSortedByPrice( list: MutableLiveData<MutableList<Commodity>>): MutableLiveData<MutableList<Commodity>> =
        withContext(ioDispatcher) {
            list.postValue(roomDBDao.getCommodityByPrice())
            return@withContext list
        }


}