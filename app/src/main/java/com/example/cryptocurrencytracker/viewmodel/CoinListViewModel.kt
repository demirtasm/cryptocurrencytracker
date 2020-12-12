package com.example.cryptocurrencytracker.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencytracker.api.models.Coin
import com.example.cryptocurrencytracker.api.models.DetailCoin
import com.example.cryptocurrencytracker.services.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class CoinListViewModel : ViewModel() {
    private var _coins = MutableLiveData<Coin>()
    val coins: LiveData<Coin>
        get() = _coins

    private var detailCoins = MutableLiveData<DetailCoin>()
    val detail: LiveData<DetailCoin>
        get() = detailCoins

    fun getData() {
        viewModelScope.launch {
            try {
                _coins.value = RetrofitInstance.retrofitInstance.getCoinList()
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        }
    }

    fun getDetail(id: String) {
        viewModelScope.launch {
            try {
                detailCoins.value = RetrofitInstance.retrofitInstance.getDetailCoin(id)
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        }
    }
}