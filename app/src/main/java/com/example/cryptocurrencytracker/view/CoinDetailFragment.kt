package com.example.cryptocurrencytracker.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cryptocurrencytracker.databinding.FragmentCoinDetailBinding
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.Observer

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            id = CoinDetailFragmentArgs.fromBundle(it).id
            viewModel.getDetail(id.toString())
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getDetail(id.toString())
            }
            viewModel.detail.observe(viewLifecycleOwner, Observer { coinDetail ->
                Log.e("TAG", "coinDetail: $coinDetail")
            })
        }
    }
}