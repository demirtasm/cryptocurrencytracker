package com.example.cryptocurrencytracker.api.models

data class MarketData(
    val current_price: CurrentPrice,
    val high_24h: High24h
)