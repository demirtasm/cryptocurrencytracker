package com.example.cryptocurrencytracker.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.cryptocurrencytracker.api.models.Coin
import com.example.cryptocurrencytracker.api.models.CoinItem
import com.example.cryptocurrencytracker.api.models.DetailCoin
import com.example.cryptocurrencytracker.databinding.FragmentCoinDetailBinding
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.fragment_coin_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CoinDetailFragment : Fragment() {

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var binding: FragmentCoinDetailBinding
    private var id = "undefined"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewmodel = this@CoinDetailFragment.viewModel
            }

        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            id = CoinDetailFragmentArgs.fromBundle(it).id
            viewModel.getDetail(id)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getDetail(id)
            }
            viewModel.detail.observe(viewLifecycleOwner, Observer { coinDetail ->
                fillDetail(coinDetail)

            })
        }
    }
    fun fillDetail(v: DetailCoin){
        Glide.with(this)
            .load(v.image.large)
            .into(imgDetail)
        //.error(R.drawable.ic_broken_image)
        val collapsingToolbar = collapsingToolbar as CollapsingToolbarLayout
        collapsingToolbar.title = v.name
        tvHashing.text = v.hashing_algorithm
        tvCurrentPriceTr.text = v.market_data.current_price.`try`.toString()
        tvCurrentPriceUsd.text = v.market_data.current_price.`usd`.toString()
        tvCurrentPriceAed.text = v.market_data.current_price.`aed`.toString()
        tvCurrentPriceArs.text = v.market_data.current_price.`ars`.toString()
        tvCurrentPriceAud.text = v.market_data.current_price.`aud`.toString()
        tvCurrentPriceBtc.text = v.market_data.current_price.`btc`.toString()
        tvCurrentPriceDot.text = v.market_data.current_price.`dot`.toString()
        tvHigh24hTry.text = v.market_data.high_24h.`try`.toString()
        tvHigh24hUsd.text = v.market_data.high_24h.`usd`.toString()
        tvHigh24hAed.text = v.market_data.high_24h.`aed`.toString()
        tvHigh24hArs.text = v.market_data.high_24h.`ars`.toString()
        tvHigh24hAud.text = v.market_data.high_24h.`aud`.toString()
        tvHigh24hBtc.text = v.market_data.high_24h.`btc`.toString()
        tvHigh24hDot.text = v.market_data.high_24h.`dot`.toString()
        tvDescription.text = v.description.`try`
    }
}