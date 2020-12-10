package com.example.cryptocurrencytracker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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