package com.example.cryptocurrencytracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptocurrencytracker.R
import androidx.lifecycle.Observer
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cryptocurrencytracker.databinding.ActivityCoinDetailBinding
import com.example.cryptocurrencytracker.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinDetailActivity : AppCompatActivity() {

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var binding: ActivityCoinDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coin_detail)
       binding.apply {
            lifecycleOwner = this@CoinDetailActivity
        }

        var intent = intent
        var id = intent.extras?.get("id")
        viewModel.getDetail(id.toString())
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getDetail(id.toString())
        }
        viewModel.detail.observe(this, Observer { coinDetail ->
            Log.e("TAG", "coinDetail: $coinDetail")
        })
    }
}