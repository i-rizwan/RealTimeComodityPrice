package com.infigo.realtimecomodityapp.networkCall

import com.android.volley.VolleyError

interface ResponseListener {
     fun onSuccess(response:String)
     fun onError(volleyError: VolleyError)
}
