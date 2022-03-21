package com.infigo.realtimecomodityapp.models

import androidx.room.Entity

@Entity(tableName = "commodity", primaryKeys = ["district", "commodity"])
data class Commodity(
    val state: String,
    val district: String,
    val market: String,
    val commodity: String,
    val variety: String,
    val arrival_date: String,
    val min_price: Double,
    val max_price: Double,
    val modal_price: Double
)