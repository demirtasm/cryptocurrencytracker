package com.example.cryptocurrencytracker.api.data.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_list")
data class CoinListEntity(
    @PrimaryKey
    val id: String?,
    val name: String?,
    val symbol: String?
)