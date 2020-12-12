package com.example.cryptocurrencytracker.api.models

data class DetailCoin(
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val name: String,
    val symbol: String,
    val market_data: MarketData,
    val description: Descriptions
)