package com.example.cryptocurrencytracker.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencytracker.api.data.entitiy.CoinListEntity
import com.example.cryptocurrencytracker.services.RetrofitInstance
import kotlinx.coroutines.launch

class CoinListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var _coins = MutableLiveData<List<CoinListEntity>>()
    val coins: LiveData<List<CoinListEntity>>
        get() = _coins

    fun getData() {
        viewModelScope.launch {
            try {
                _coins.value = RetrofitInstance.retrofitInstance.getCoinList()
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        }
    }
}