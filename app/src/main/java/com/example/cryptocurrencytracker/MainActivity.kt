package com.example.cryptocurrencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CoinListViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }

        viewModel.coins.observe(this, Observer { userList ->
            Log.e("TAG", "$userList")
        })
    }
}