package com.example.cryptocurrencytracker.services

import com.example.cryptocurrencytracker.api.models.Coin
import retrofit2.http.GET

interface ApiInterfaces {
    @GET("api/v3/coins/list")
    suspend fun getCoinList(): Coin
}