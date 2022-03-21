package com.infigo.realtimecomodityapp.viewModel

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.infigo.realtimecomodityapp.models.Commodity
import com.infigo.realtimecomodityapp.networkCall.ResponseListener
import com.infigo.realtimecomodityapp.networkCall.Repository
import com.infigo.realtimecomodityapp.networkCall.NetworkHelper
import com.infigo.realtimecomodityapp.utils.LocalPreference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class CommodityRemoteRepo(private val repository: Repository, private val context: Context) {


    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private var networkHelper: NetworkHelper = NetworkHelper.getInstance(context)

    val queue: RequestQueue =networkHelper.requestQueue


    suspend fun saveMovies(list: MutableList<Commodity>) {
        withContext(ioDispatcher){
            repository.saveData(list)
        }
    }

    fun getCommodity(){

        val request=networkHelper.getRequest(LocalPreference.url,object : ResponseListener {

            override   fun onSuccess(response: String) {
                Log.d("Commodity Response",response)
                 runBlocking {
                     saveMovies(parseResponse(response))
                 }

            }

            override  fun onError(volleyError: VolleyError) {
                Log.d("Commodity Response ","onError  ${volleyError.networkResponse}")
            }

        })
        queue.add(request)
    }

    private fun parseResponse(response:String):MutableList<Commodity>{

        val mainObject= JSONObject(response)

        val list:MutableList<Commodity> = mutableListOf()

        val recordsArray: JSONArray =mainObject.getJSONArray("records")

        for (i in 0 until recordsArray.length()){
            val commodityObject=recordsArray[i] as JSONObject

         //   val timeStamp=commodityObject.getDouble("timestamp")
            val state=commodityObject.getString("state")
            val district=commodityObject.getString("district")
            val market=commodityObject.getString("market")
            val commodity=commodityObject.getString("commodity")
            val varity=commodityObject.getString("variety")
            val date=commodityObject.getString("arrival_date")
            val min_price=commodityObject.getDouble("min_price")
            val max_price=commodityObject.getDouble("max_price")
            val modal_price=commodityObject.getDouble("modal_price")

            val commodityObj= Commodity(
              //  timeStamp,
                state,
                district,
                market,
                commodity,
                varity,
                date,
                min_price,
                max_price,
                modal_price
            )
            list.add(commodityObj)
        }
        Log.d("VolleyHelper","getRequest ${list.size}")

         return list
    }
}