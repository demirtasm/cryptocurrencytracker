package com.example.cryptocurrencytracker.services

interface AuthInterface {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}