package com.example.cryptocurrencytracker.services

import com.example.cryptocurrencytracker.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {
    companion object {
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitInstance: ApiInterfaces by lazy {
            retrofit.create(ApiInterfaces::class.java)
        }
    }
}