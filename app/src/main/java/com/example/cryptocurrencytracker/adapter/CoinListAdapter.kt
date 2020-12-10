package com.example.cryptocurrencytracker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencytracker.api.models.CoinItem
import kotlinx.android.synthetic.main.coin_items.view.*

class CoinListAdapter(allCoins: ArrayList<CoinItem>) :
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
        holder.itemView.setOnClickListener{v->
            Log.e("Click","Coins")
            //Detail
        }

    }
    fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if(queryString== null || queryString.isBlank()){
                    Log.e("filterResults","empty"+coins)
                   coins
                }else{
                    Log.e("filterResults","Not empty")
                    coins.filter{
                        it.name.toLowerCase().contains(queryString)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                coins = results?.values as ArrayList<CoinItem>
                notifyDataSetChanged()
            }

        }
    }
    fun updateDataList(newDataList: List<CoinItem>){
        coins.clear()
        coins.addAll(newDataList)
        notifyDataSetChanged()
    }
    class CoinListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var coinViewList = itemView as CardView
        var coinName = coinViewList.tvTitle
    }
}