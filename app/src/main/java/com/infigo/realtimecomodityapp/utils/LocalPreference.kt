package com.infigo.realtimecomodityapp.utils

import com.infigo.realtimecomodityapp.models.FilterValue

object LocalPreference {

    var isFirstLaunch:Boolean=false
    val districtList= arrayListOf("Durg","Chittor","Kurnool","Bilaspur","Kabirdham" ,"Balodabazar","Tiphra","Nagari")
    val priceList= arrayListOf("2000","2500","3000")

    const val district=0

    const val price=1

    var filterMap = HashMap<Int, FilterValue>()
    const val url=" https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=50"




}