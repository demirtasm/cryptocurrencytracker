package com.example.cryptocurrencytracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencytracker.R
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

        initRecyclerView()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }

        viewModel.coins.observe(this, Observer { coinsList ->
            Log.e("TAG", "$coinsList")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Arama"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                coinsListAdapter.getFilter().filter(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
    fun initRecyclerView() {
        viewModel = ViewModelProvider(this).get(CoinListViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = coinsListAdapter
    }

    override fun onStart() {
        super.onStart()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.coins.observe(this, Observer { data ->
            data?.let {
                recyclerView.visibility = View.VISIBLE
                coinsListAdapter.updateDataList(data)
            }
        })
    }
}