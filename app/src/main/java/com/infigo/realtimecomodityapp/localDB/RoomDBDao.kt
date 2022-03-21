package com.infigo.realtimecomodityapp.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infigo.realtimecomodityapp.models.Commodity

@Dao
interface RoomDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCommodities(list: MutableList<Commodity>)

    @Query("SELECT * FROM commodity ORDER BY district ASC")
    fun getCommodityByDistrict(): LiveData<MutableList<Commodity>>

//    @Query("select * from commodity order by timeStamp asc")
//    fun gettCommodities():MutableList<Commodity>

    @Query("SELECT * FROM commodity ORDER BY min_price ASC")
    fun getCommodityByPrice(): MutableList<Commodity>

    @Query("select * from commodity where (min_price >= (:minPrice)) or (district in (:districtList))")
    fun getFilter(minPrice: ArrayList<String>,districtList: ArrayList<String>):MutableList<Commodity>

    @Query("select * from commodity where (district in (:districtList))")
    fun getDistrictFilter(districtList: ArrayList<String>):MutableList<Commodity>

    @Query("select * from commodity where (min_price >= (:minPrice))")
    fun getPriceFilter(minPrice: ArrayList<String>):MutableList<Commodity>
}