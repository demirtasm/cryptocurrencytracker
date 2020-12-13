package com.example.cryptocurrencytracker

import android.app.Application
import com.example.cryptocurrencytracker.api.data.FirebaseSource
import com.example.cryptocurrencytracker.api.repositories.CoinListRepository
import com.example.cryptocurrencytracker.api.repositories.FirebaseUserRepository
import com.example.cryptocurrencytracker.viewmodel.AuthViewModelFactory
import com.example.cryptocurrencytracker.viewmodel.CoinListViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CryptocurrencyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@CryptocurrencyApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { FirebaseUserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from singleton { CoinListRepository(instance()) }
        bind() from provider { CoinListViewModel(instance()) }
    }
}