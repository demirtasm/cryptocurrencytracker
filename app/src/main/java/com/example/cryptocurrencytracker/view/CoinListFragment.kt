package com.example.cryptocurrencytracker.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencytracker.R
import com.example.cryptocurrencytracker.adapter.CoinListAdapter
import com.example.cryptocurrencytracker.databinding.FragmentCoinListBinding
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.fragment_coin_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinListFragment : Fragment() {
    private val viewModel: CoinListViewModel by viewModels()
    private var coinsListAdapter = CoinListAdapter(arrayListOf())
    private lateinit var binding: FragmentCoinListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCoinListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewmodel = this@CoinListFragment.viewModel
            }

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Arama"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                coinsListAdapter.getFilter().filter(newText)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initRecyclerView()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // initRecyclerView()

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }

        viewModel.coins.observe(this, Observer { coinsList ->
            Log.e("TAG", "$coinsList")
        })
    }

    fun initRecyclerView() {
        //viewModel = ViewModelProvider(this).get(CoinListViewModel::class.java)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = coinsListAdapter
        }
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