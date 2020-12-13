package com.example.cryptocurrencytracker.api.repositories

import androidx.lifecycle.LiveData
import com.example.cryptocurrencytracker.api.data.dao.CoinListDao
import com.example.cryptocurrencytracker.api.data.entitiy.CoinListEntity

class CoinListRepository(private val coinListDao: CoinListDao) {
    fun allCoins(): LiveData<List<CoinListEntity>> = coinListDao.coinsList()
}