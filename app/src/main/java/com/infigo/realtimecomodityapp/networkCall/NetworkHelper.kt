package com.infigo.realtimecomodityapp.networkCall

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class NetworkHelper constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: NetworkHelper? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: NetworkHelper(context).also {
                        INSTANCE = it
                    }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }


    fun getRequest(url: String, responseListener: ResponseListener): JsonObjectRequest {
        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,

            Response.Listener { response ->
                responseListener.onSuccess(response.toString())
            },
            Response.ErrorListener { error ->
                responseListener.onError(error)

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        return jsonObjectRequest
    }


}