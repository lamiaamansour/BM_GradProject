package com.example.currencyconverter.data.local.dp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CachedCurrency(
    @PrimaryKey val code: String,
    val name: String,
    val icon_url: String
)
