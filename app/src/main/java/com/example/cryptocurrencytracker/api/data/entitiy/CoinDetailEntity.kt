package com.example.cryptocurrencytracker.api.data.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencytracker.api.models.Descriptions
import com.example.cryptocurrencytracker.api.models.Image
import com.example.cryptocurrencytracker.api.models.MarketData

@Entity(tableName = "coins_detail")
data class CoinDetailEntity(
    @PrimaryKey
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val name: String,
    val symbol: String,
    val market_data: MarketData,
    val description: Descriptions
)