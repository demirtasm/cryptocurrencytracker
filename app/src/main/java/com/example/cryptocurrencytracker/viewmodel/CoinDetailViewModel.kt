package com.example.cryptocurrencytracker.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencytracker.api.models.DetailCoin
import com.example.cryptocurrencytracker.services.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class CoinDetailViewModel : ViewModel() {
    private var detailCoins = MutableLiveData<DetailCoin>()
    val detail: LiveData<DetailCoin>
        get() = detailCoins

    fun getDetail(id: String) {
        viewModelScope.launch {
            try {
                detailCoins.value = RetrofitInstance.retrofitInstance.getDetailCoin(id)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Detail Coin Error: ${e.message}")
            }
        }
    }
    fun addingFavourite() {

    }
}