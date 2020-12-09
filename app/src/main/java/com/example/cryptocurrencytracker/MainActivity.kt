package com.example.cryptocurrencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencytracker.adapter.CoinListAdapter
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinListViewModel
    private var coinsListAdapter = CoinListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initRecyclerView()
        viewModel = ViewModelProvider(this).get(CoinListViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = coinsListAdapter


        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }

        viewModel.coins.observe(this, Observer { coinsList ->
           // Log.e("TAG", "$coinsList")
            for(i in 0 until coinsList.size){

            }
        })
    }

    override fun onStart() {
        super.onStart()
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.coins.observe(this, Observer { data->
            data?.let{
                recyclerView.visibility = View.VISIBLE
                coinsListAdapter.updateDataList(data)
            }
        })
    }
}