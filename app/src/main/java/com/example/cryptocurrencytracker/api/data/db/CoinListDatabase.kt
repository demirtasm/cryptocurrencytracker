package com.example.cryptocurrencytracker.api.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptocurrencytracker.api.data.dao.CoinListDao
import com.example.cryptocurrencytracker.api.data.entitiy.CoinListEntity

@Database(entities = arrayOf(CoinListEntity::class), version = 1, exportSchema = false)
abstract class CoinListDatabase : RoomDatabase() {

    abstract fun coinListDao(): CoinListDao

     companion object {
         @Volatile
         private var coinListInstance: CoinListDatabase? = null

         fun getDatabase(context: Context): CoinListDatabase? {
             return coinListInstance ?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     CoinListDatabase::class.java,
                     "coin_database"
                 ).build()
                 coinListInstance = instance
                 instance
             }
         }
     }
}