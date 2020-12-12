package com.example.cryptocurrencytracker.services

import com.example.cryptocurrencytracker.api.models.Coin
import com.example.cryptocurrencytracker.api.models.DetailCoin
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaces {
    @GET("api/v3/coins/list")
    suspend fun getCoinList(): Coin

    @GET("api/v3/coins/{id}")
    suspend fun getDetailCoin( @Path("id") id: String):DetailCoin
}