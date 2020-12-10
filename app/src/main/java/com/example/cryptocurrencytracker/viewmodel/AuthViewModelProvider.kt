package com.example.cryptocurrencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencytracker.api.repositories.FirebaseUserRepository


class AuthViewModelProvider(private val repository: FirebaseUserRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}