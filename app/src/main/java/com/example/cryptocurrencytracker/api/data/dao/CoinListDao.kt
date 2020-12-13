package com.example.cryptocurrencytracker.api.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.cryptocurrencytracker.api.data.entitiy.CoinListEntity
@Dao
interface CoinListDao {
    @Query("Select * from coin_list")
     fun coinsList(): LiveData<List<CoinListEntity>>

    @Update
    suspend fun updateCoinListEntity(data: CoinListEntity):Int

}