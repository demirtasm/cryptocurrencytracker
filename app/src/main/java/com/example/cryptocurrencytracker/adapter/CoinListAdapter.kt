package com.example.cryptocurrencytracker.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencytracker.api.data.entitiy.CoinListEntity
import com.example.cryptocurrencytracker.api.models.Coin
import com.example.cryptocurrencytracker.api.models.CoinItem
import com.example.cryptocurrencytracker.view.CoinDetailFragment
import com.example.cryptocurrencytracker.view.CoinListFragment
import com.example.cryptocurrencytracker.view.CoinListFragmentDirections
import kotlinx.android.synthetic.main.coin_items.view.*

class CoinListAdapter(allCoins: ArrayList<CoinListEntity>) :
    RecyclerView.Adapter<CoinListAdapter.CoinListHolder>() {

    var coins = allCoins

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListHolder {
        var inflater = LayoutInflater.from(parent.context)
        var coinsLayoutView =
            inflater.inflate(com.example.cryptocurrencytracker.R.layout.coin_items, parent, false)
        return CoinListHolder(coinsLayoutView)
    }

    override fun onBindViewHolder(holder: CoinListHolder, position: Int) {
        holder.coinName.text = coins.get(position).name
        holder.itemView.setOnClickListener { v ->
            val coinDetailAction =
                CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment()
          coinDetailAction.id = coins.get(position).id!!
            Navigation.findNavController(v).navigate(coinDetailAction)
        }

    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString == null || queryString.isBlank()) {
                    coins
                } else {
                    coins.filter {
                        it.name?.toLowerCase()?.contains(queryString)!!
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                coins = results?.values as ArrayList<CoinListEntity>
                notifyDataSetChanged()
            }

        }
    }

    fun updateDataList(newDataList: List<CoinListEntity>) {
        coins.clear()
        coins.addAll(newDataList)
        notifyDataSetChanged()
    }

    class CoinListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var coinViewList = itemView as CardView
        var coinName = coinViewList.tvTitle
    }
}