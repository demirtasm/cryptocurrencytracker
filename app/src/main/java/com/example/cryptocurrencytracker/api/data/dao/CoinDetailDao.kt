package com.example.cryptocurrencytracker.api.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.cryptocurrencytracker.api.data.entitiy.CoinListEntity

interface CoinDetailDao {
    @Query("Select * from coins_detail")
    fun coinsDetail(): LiveData<List<CoinDetailDao>>
}